package com.example.TicketManagement.controller;


import com.example.TicketManagement.dto.request.MessageRequestDTO;
import com.example.TicketManagement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{chatId}")
    public String addMessage(@PathVariable Long chatId , @RequestBody MessageRequestDTO messageRequestDTO){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        return messageService.addMessage(chatId ,messageRequestDTO, auth);
    }



}
