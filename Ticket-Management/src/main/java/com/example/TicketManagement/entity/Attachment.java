package com.example.TicketManagement.entity;


import jakarta.persistence.*;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filetype;
    private String description;
    private String fileName;


}
