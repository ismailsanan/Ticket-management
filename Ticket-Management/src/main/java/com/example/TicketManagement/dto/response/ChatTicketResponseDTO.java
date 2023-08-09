package com.example.TicketManagement.dto.response;

import com.example.TicketManagement.entity.Message;
import com.example.TicketManagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChatTicketResponseDTO {
    private Long id;

    private String customer;

    private String expert;

    private List<Message> messages;

    private String title;

}
