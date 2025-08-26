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
    List<Connection> findByInitiatorId(Long userId);
    List<Connection> findByReceiverId(Long userId);

    @Query("SELECT c.receiver FROM Connection c WHERE c.initiator.id = :userId AND c.status = 'CONNECTED' " +
            "UNION " +
            "SELECT c.initiator FROM Connection c WHERE c.receiver.id = :userId AND c.status = 'CONNECTED'")
    List<User> findAllConnectedUsers(@Param("userId") Long userId);


    @Query("SELECT c FROM Connection c " +
            "WHERE (c.receiver.id = :userId) " +
            "AND c.status = 'PENDING'")
    List<Connection> findConnectionRequests(@Param("userId") Long userId);

    @Query("SELECT c FROM Connection c " +
            "WHERE (c.initiator.id = :userId OR c.receiver.id = :userId) " +
            "AND c.status = 'CONNECTED'")
    List<Connection> findAllConnections(@Param("userId") Long userId);
}
