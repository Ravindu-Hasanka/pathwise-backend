package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.Auth.AuthenticationResponse;
import com.example.pathwisebackend.DTO.Auth.RegisterRequestDto;
import com.example.pathwisebackend.Enum.UserRoles;
import com.example.pathwisebackend.Models.*;
import com.example.pathwisebackend.Repositories.*;
import com.example.pathwisebackend.config.JwtService;
import com.example.pathwisebackend.config.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final SkillsRepository skillsRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final JwtService jwtService;
    private final AuthTokenRepository authTokenRepository;

    public AuthenticationResponse register(RegisterRequestDto dto) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return new AuthenticationResponse(null, null, null, "User already exists");
        }

        if(dto.getRole().equals(UserRoles.COACH)){
            Coach coach = new Coach();
            coach.setName(dto.getName());
            coach.setEmail(dto.getEmail());
            coach.setPassword(passwordEncoder.encode(dto.getPassword()));
            coach.setPhone(dto.getContactNo());
            coach.setRole(dto.getRole());
            coach.setAddress(dto.getAddress());
            coach.setDescription(dto.getDescription());

            List<Industry> industries = Arrays.stream(dto.getIndustryList())
                    .map(industryDto -> {
                        Industry industry = new Industry();
                        industry.setName(industryDto.getIndustryName());
                        industry.setOwner(coach);
                        return industry;
                    })
                    .toList();
            coach.setIndustries(industries);

            Set<Skill> skills = Arrays.stream(dto.getSkillList())
                    .map(skillDto -> {
                        Skill skill = new Skill();
                        skill.setName(skillDto.getSkillName());
                        return skillsRepository.save(skill); // persist immediately
                    })
                    .collect(Collectors.toSet());
            coach.setSkills(skills);

            Set<Experience> experiences = Arrays.stream(dto.getExperienceList())
                    .map(expDto -> {
                        Experience exp = new Experience();
                        exp.setJobRole(expDto.getJobTitle());
                        exp.setCompany(expDto.getCompanyName());
                        exp.setStartedAt(expDto.getStartedAt());
                        exp.setEndedAt(expDto.getEndedAt());
                        return experienceRepository.save(exp);
                    })
                    .collect(Collectors.toSet());
            coach.setExperiences(experiences);

            Set<Education> educations = Arrays.stream(dto.getEducationList())
                    .map(eduDto -> {
                        Education edu = new Education();
                        edu.setName(eduDto.getEducationName());
                        edu.setInstitution(eduDto.getInstitute());
                        edu.setStartDate(eduDto.getStartedAt());
                        edu.setEndDate(eduDto.getEndedAt());
                        return educationRepository.save(edu);
                    })
                    .collect(Collectors.toSet());
            coach.setEducations(educations);

            Coach savedUser = userRepository.save(coach);

            String accessToken = jwtService.generateToken(savedUser);
            String refreshToken = jwtService.generateRefreshToken(savedUser);

            return new AuthenticationResponse(
                    savedUser.getId(),
                    accessToken,
                    refreshToken,
                    "Registration successful"
            );
        }
        else {

            // 2. Create JobSeeker (default role = JOB_SEEKER)
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setName(dto.getName());
            jobSeeker.setEmail(dto.getEmail());
            jobSeeker.setPassword(passwordEncoder.encode(dto.getPassword()));
            jobSeeker.setPhone(dto.getContactNo());
            jobSeeker.setRole(dto.getRole());
            jobSeeker.setAddress(dto.getAddress());
            jobSeeker.setCurrentPosition(dto.getCurrentPosition());
            jobSeeker.setCurrentIndustry(dto.getCurrentIndustry());

            List<Industry> industries = Arrays.stream(dto.getIndustryList())
                    .map(industryDto -> {
                        Industry industry = new Industry();
                        industry.setName(industryDto.getIndustryName());
                        industry.setOwner(jobSeeker);
                        return industry;
                    })
                    .toList();
            jobSeeker.setIndustries(industries);

            Set<Skill> skills = Arrays.stream(dto.getSkillList())
                    .map(skillDto -> {
                        Skill skill = new Skill();
                        skill.setName(skillDto.getSkillName());
                        skill.setCreatedAt(new Date(System.currentTimeMillis()));
                        return skillsRepository.save(skill); // persist immediately
                    })
                    .collect(Collectors.toSet());
            jobSeeker.setSkills(skills);

            Set<Experience> experiences = Arrays.stream(dto.getExperienceList())
                    .map(expDto -> {
                        Experience exp = new Experience();
                        exp.setJobRole(expDto.getJobTitle());
                        exp.setCompany(expDto.getCompanyName());
                        exp.setStartedAt(expDto.getStartedAt());
                        exp.setEndedAt(expDto.getEndedAt());
                        return experienceRepository.save(exp);
                    })
                    .collect(Collectors.toSet());
            jobSeeker.setExperiences(experiences);

            Set<Education> educations = Arrays.stream(dto.getEducationList())
                    .map(eduDto -> {
                        Education edu = new Education();
                        edu.setName(eduDto.getEducationName());
                        edu.setInstitution(eduDto.getInstitute());
                        edu.setStartDate(eduDto.getStartedAt());
                        edu.setEndDate(eduDto.getEndedAt());
                        return educationRepository.save(edu);
                    })
                    .collect(Collectors.toSet());
            jobSeeker.setEducations(educations);

            JobSeeker savedUser = userRepository.save(jobSeeker);

            String accessToken = jwtService.generateToken(savedUser);
            String refreshToken = jwtService.generateRefreshToken(savedUser);

            return new AuthenticationResponse(
                    savedUser.getId(),
                    accessToken,
                    refreshToken,
                    "Registration successful"
            );
        }
    }

    public AuthenticationResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new AuthenticationResponse(null,null, null, "Invalid email or password");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        AuthToken authToken = new AuthToken();
        authToken.setToken(refreshToken);
        authToken.setIssuedAt(new java.sql.Date(System.currentTimeMillis()));
        authToken.setExpiresAt(new java.sql.Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)); // 7 days
        authToken.setIsValid(true);
        authToken.setUser(user);

        authTokenRepository.save(authToken);

        return new AuthenticationResponse(user.getId(),accessToken, refreshToken, "Login successful");
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        // 1. Validate refresh token in DB
        AuthToken storedToken = authTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!storedToken.getIsValid() || storedToken.getExpiresAt().before(new java.util.Date())) {
            throw new RuntimeException("Refresh token expired or invalid");
        }

        // 2. Get user
        User user = storedToken.getUser();

        // 3. Generate new access token
        String newAccessToken = jwtService.generateToken(user);

        return new AuthenticationResponse(
                user.getId(),
                newAccessToken,
                refreshToken,  // reuse the same refresh token
                "Token refreshed successfully"
        );
    }

}
