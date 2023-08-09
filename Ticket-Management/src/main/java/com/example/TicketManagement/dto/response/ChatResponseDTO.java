package com.example.TicketManagement.dto.response;

import com.example.TicketManagement.entity.Message;
import com.example.TicketManagement.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChatResponseDTO {

    private Long id;

    private User sender;

    private User receiver;

    private List<Message> messages;

    private String title;
}
