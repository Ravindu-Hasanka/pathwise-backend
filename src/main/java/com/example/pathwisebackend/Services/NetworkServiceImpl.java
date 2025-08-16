    package com.example.pathwisebackend.Services;

    import com.example.pathwisebackend.Models.Connection;
    import com.example.pathwisebackend.Models.ConnectionStatus;
    import com.example.pathwisebackend.Models.User;
    import com.example.pathwisebackend.Repositories.ConnectionRepository;
    import com.example.pathwisebackend.Repositories.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class NetworkServiceImpl implements INetworkService  {
        private final UserRepository userRepo;
        private final ConnectionRepository connRepo;

        public User createUser(User user) {
            return userRepo.save(user);
        }

        public Connection requestConnection(Long requestedUserId, Long userId) {
            User requestedUser = userRepo.findById(requestedUserId).orElseThrow();
            User user = userRepo.findById(userId).orElseThrow();

            Connection conn = new Connection();
            conn.setRequestedUser(requestedUser);
            conn.setUser(user);
            return connRepo.save(conn);
        }
        public Connection acceptConnection(Long connectionId) {
            Connection connection = connRepo.findById(connectionId).orElseThrow();

            connection.setStatus(ConnectionStatus.CONNECTED);
            connection.setUpdatedAt(LocalDateTime.now());
            return connRepo.save(connection);
        }

        public Connection ignoreConnectionReq(Long connectionId) {
            Connection connection = connRepo.findById(connectionId).orElseThrow();

            connection.setStatus(ConnectionStatus.IGNORED);
            return connRepo.save(connection);
        }

        public List<User> getAllConnectedUsers(Long userId) {
            return connRepo.findAllConnectedUsers(userId);
        }
        public List<User> getConnectionRequests(Long userId) {

            return connRepo.findAllConnectionRequests(userId);
        }

    }
