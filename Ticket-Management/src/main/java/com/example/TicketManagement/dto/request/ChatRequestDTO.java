package com.example.TicketManagement.dto.request;


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

public class ChatRequestDTO {

    private User sender;

    private List<Message> messages;

    private String title;
}
