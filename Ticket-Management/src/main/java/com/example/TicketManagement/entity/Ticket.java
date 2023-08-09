package com.example.TicketManagement.entity;


import com.example.TicketManagement.enumeration.Priority;
import com.example.TicketManagement.enumeration.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String title;

    private String description;

    @OneToOne
    private Chat chat;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}



