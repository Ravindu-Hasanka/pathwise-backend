package com.example.pathwisebackend.Models;

import com.example.pathwisebackend.Enum.ContentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    //@NotBlank(message = "Caption is required")
    @Column(length = 200)
    private String caption;

    @NotBlank(message = "Content is required")
    @Column(length = 5000)
    private String content;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Content type is required")
    private ContentType contentType;

    @NotNull(message = "Created date is required")
    private Date createdAt;

    private Date updatedAt = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @NotNull(message = "User is required")
    private User createdBy;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
