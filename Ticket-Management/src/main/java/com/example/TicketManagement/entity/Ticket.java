package com.example.TicketManagement.entity;


import com.example.TicketManagement.enumeration.Priority;
import com.example.TicketManagement.enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}



