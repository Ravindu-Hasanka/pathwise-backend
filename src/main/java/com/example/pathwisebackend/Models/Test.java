package com.example.pathwisebackend.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    private String id;

    @NotBlank(message = "Message is required")
    @Size(max = 50, message = "Message cannot exceed 50 characters")
    private String testMessage;
}
