package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Connection;
import com.example.pathwisebackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    @Query("SELECT c.requestedUser FROM Connection c WHERE c.user.userId = :userId AND c.status = 'CONNECTED' " +
            "UNION " +
            "SELECT c.user FROM Connection c WHERE c.requestedUser.userId = :userId AND c.status = 'CONNECTED'")
    List<User> findAllConnectedUsers(@Param("userId") Long userId);

    @Query("SELECT c.requestedUser FROM Connection c WHERE c.user.userId = :userId AND c.status = 'PENDING' " +
            "UNION " +
            "SELECT c.user FROM Connection c WHERE c.requestedUser.userId = :userId AND c.status = 'PENDING'")
    List<User> findAllConnectionRequests(@Param("userId") Long userId);
}
