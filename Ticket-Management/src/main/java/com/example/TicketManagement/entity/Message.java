package com.example.TicketManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User sender; //who sent the message


    private String Title;

    private String content;

    @ManyToOne
    private Chat chat;


    @OneToMany
    private List<Attachment> attachments;


    private LocalDateTime timestamp;
}
