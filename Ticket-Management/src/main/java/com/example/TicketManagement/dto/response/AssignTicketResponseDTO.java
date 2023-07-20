package com.example.TicketManagement.dto.response;


import lombok.Data;

@Data
public class AssignTicketResponseDTO {

    private Long id;
    private String filetype;
    private String description;
    private String fileName;
}
