package com.example.TicketManagement.service;


import com.example.TicketManagement.dto.request.MessageRequestDTO;
import com.example.TicketManagement.entity.Chat;
import com.example.TicketManagement.entity.Message;
import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.exception.IdNotFoundException;
import com.example.TicketManagement.repository.ChatRepository;
import com.example.TicketManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final ChatRepository chatRespository;
    private final UserRepository userRepository;

    public String addMessage(Long chatId, MessageRequestDTO messageRequestDTO , Authentication username ) {
        Chat chat = chatRespository.findById(chatId).orElseThrow(() -> new IdNotFoundException("Chat ID not found"));
        User sender = userRepository.findByEmail(username.getName()).orElseThrow(() -> new UsernameNotFoundException("Username Not found"));

        Message message = Message.builder()
                .chat(chat)
                .sender(sender)
                .content(messageRequestDTO.getContent())
                .build();

        chat.getMessages().add(message);
        chatRespository.save(chat);

        return "Message added Successfully ";
    }
}
