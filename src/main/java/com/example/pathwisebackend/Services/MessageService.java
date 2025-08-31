package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Interfaces.IMessageService;
import com.example.pathwisebackend.Models.Message;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getConversation(User sender, User receiver) {
        return messageRepository.getConversation(
                sender, receiver
        );
    }
}
