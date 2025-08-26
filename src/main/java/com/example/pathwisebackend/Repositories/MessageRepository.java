package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Message;
import com.example.pathwisebackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiverOrReceiverAndSender(
            User sender, User receiver,
            User receiver2, User sender2
    );
}

