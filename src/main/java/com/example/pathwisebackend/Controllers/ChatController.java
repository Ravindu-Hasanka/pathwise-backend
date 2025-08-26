package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.MessageDTO;
import com.example.pathwisebackend.DTO.MessageResponseDTO;
import com.example.pathwisebackend.Models.Message;
import com.example.pathwisebackend.Models.User;
import com.example.pathwisebackend.Repositories.UserRepository;
import com.example.pathwisebackend.Services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload MessageDTO messageDTO) {

        User sender = userRepository.findById(messageDTO.getSenderId()).orElseThrow();
        User receiver = userRepository.findById(messageDTO.getReceiverId()).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDTO.getContent());
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageService.saveMessage(message);

        MessageResponseDTO dto = new MessageResponseDTO();

        dto.setId(savedMessage.getId());
        dto.setContent(savedMessage.getContent());
        dto.setSenderName(savedMessage.getSender().getName());
        dto.setTimestamp(savedMessage.getTimestamp());

        messagingTemplate.convertAndSendToUser(
                receiver.getUsername(), "/queue/messages", dto
        );
    }


    @GetMapping("/messages/{senderId}/{receiverId}")
    @ResponseBody
    public List<MessageResponseDTO> getConversation(@PathVariable Long senderId, @PathVariable Long receiverId) {
        User sender = userRepository.findById(senderId).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();

        List<Message> messages = messageService.getConversation(sender, receiver);

        return messages.stream().map(msg -> {
            MessageResponseDTO dto = new MessageResponseDTO();
            dto.setId(msg.getId());
            dto.setContent(msg.getContent());
            dto.setSenderId(msg.getSender().getId());
            dto.setSenderName(msg.getSender().getName());
            dto.setReceiverId(msg.getReceiver().getId());
            dto.setReceiverName(msg.getReceiver().getName());
            dto.setTimestamp(msg.getTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }

}

