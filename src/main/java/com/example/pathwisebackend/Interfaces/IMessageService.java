package com.example.pathwisebackend.Interfaces;

import com.example.pathwisebackend.Models.Message;
import com.example.pathwisebackend.Models.User;

import java.util.List;

public interface IMessageService {
    Message saveMessage(Message message);
    List<Message> getConversation(User sender, User receiver);
}
