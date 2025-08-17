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
    List<Connection> findByRequesterId(Long userId);
    List<Connection> findByRequestedUserId(Long userId);

    @Query("SELECT c.requestedUser FROM Connection c WHERE c.requester.id = :userId AND c.status = 'CONNECTED' " +
            "UNION " +
            "SELECT c.requester FROM Connection c WHERE c.requestedUser.id = :userId AND c.status = 'CONNECTED'")
    List<User> findAllConnectedUsers(@Param("id") Long userId);

    @Query("SELECT c FROM Connection c " +
            "WHERE (c.requester.id = :userId) " +
            "AND c.status = 'PENDING'")
    List<Connection> findAllConnectionsByUserId(@Param("userId") Long userId);
    @Query("SELECT c FROM Connection c " +
            "WHERE (c.requester.id = :userId) " +
            "AND c.status = 'CONNECTED'")
    List<Connection> findAllConnections(@Param("userId") Long userId);
}