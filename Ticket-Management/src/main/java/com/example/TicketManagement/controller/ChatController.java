package com.example.TicketManagement.controller;


import com.example.TicketManagement.entity.Chat;
import com.example.TicketManagement.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/createchat")
    public Chat createChat(){
        return chatService.createChat();
    }

}
