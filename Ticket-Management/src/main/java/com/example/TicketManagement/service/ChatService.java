package com.example.TicketManagement.service;


import com.example.TicketManagement.entity.Chat;
import com.example.TicketManagement.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat createChat() {
        Chat chat = new Chat();
        chatRepository.save(chat);
        return chat;
    }
}
