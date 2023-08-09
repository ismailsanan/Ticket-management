package com.example.TicketManagement.mapper;

import com.example.TicketManagement.dto.response.ChatResponseDTO;
import com.example.TicketManagement.entity.Chat;

public class ChatMapper {


    public static Chat mapD(ChatResponseDTO chatResponseDTO){

      return Chat.builder()
                .id(chatResponseDTO.getId())
                .customer(chatResponseDTO.getSender())
                .expert(chatResponseDTO.getReceiver())
                .messages(chatResponseDTO.getMessages())
                .title(chatResponseDTO.getTitle())
                .build();

    }

    public static  ChatResponseDTO mapE(Chat chat){

        return ChatResponseDTO.builder()
                .id(chat.getId())
                .sender(chat.getCustomer())
                .receiver(chat.getExpert())
                .messages(chat.getMessages())
                .title(chat.getTitle())
                .build();
    }



}
