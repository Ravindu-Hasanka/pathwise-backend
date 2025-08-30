package com.example.pathwisebackend.config;

import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> optionalUser = this.userRepository.findByEmail(userEmail);

                if (!optionalUser.isPresent()) {
                    throw new UsernameNotFoundException("User not found");
                }

                User user = optionalUser.get();

                if (jwtService.isTokenValid(jwt, optionalUser)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    // Token is invalid (including expired)
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token is invalid or expired");
                    return;
                }
            }
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired");
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

}