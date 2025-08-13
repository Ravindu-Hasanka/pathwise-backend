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
    @Query("SELECT c.connectedUser FROM Connection c WHERE c.user.id = :userId AND c.status = 'CONNECTED' " +
            "UNION " +
            "SELECT c.user FROM Connection c WHERE c.connectedUser.id = :userId AND c.status = 'CONNECTED'")
    List<User> findAllConnectedUsers(@Param("userId") Long userId);
}
