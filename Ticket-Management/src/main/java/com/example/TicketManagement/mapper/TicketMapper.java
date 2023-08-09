package com.example.TicketManagement.mapper;

import com.example.TicketManagement.dto.request.CreateTicketRequestDTO;
import com.example.TicketManagement.dto.response.TicketResponseDTO;
import com.example.TicketManagement.entity.Chat;
import com.example.TicketManagement.entity.Ticket;

import java.util.function.Function;


public class TicketMapper {


    private static final Function<Ticket , CreateTicketRequestDTO> mapE =
            ticket -> new CreateTicketRequestDTO();


    public static CreateTicketRequestDTO map (Ticket ticket){
        return mapE.apply(ticket);
    }
   private static final Function<Ticket, TicketResponseDTO> mapD =

           ticket -> new TicketResponseDTO(ticket.getId(), UserMapper.mapU(ticket.getUser()) ,
                   ticket.getStatus() , ticket.getPriority() , ticket.getTitle() ,
                   ticket.getDescription() ,
                   ChatMapper.mapE(ticket.getChat()),
                   ticket.getCreatedAt() ,ticket.getModifiedAt());

   public static TicketResponseDTO mapD(Ticket ticket){
       return mapD.apply(ticket);
   }


   private static final Function<TicketResponseDTO , Ticket> mapED =
    TicketResponseDTO -> new Ticket(TicketResponseDTO.getId()  , UserMapper.mapD(TicketResponseDTO.getUserDto()),TicketResponseDTO.getStatus() ,
            TicketResponseDTO.getPriority() , TicketResponseDTO.getTitle(), TicketResponseDTO.getDescription() , ChatMapper.mapD(TicketResponseDTO.getChat()),
            TicketResponseDTO.getCreatedAt() , TicketResponseDTO.getModifiedAt());




}
