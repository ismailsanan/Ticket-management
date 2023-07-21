package com.example.TicketManagement.service;


import com.example.TicketManagement.dto.request.CreateTicketRequestDTO;
import com.example.TicketManagement.dto.response.TicketResponseDTO;
import com.example.TicketManagement.entity.Ticket;
import com.example.TicketManagement.enumeration.Priority;
import com.example.TicketManagement.enumeration.Status;
import com.example.TicketManagement.exception.IdNotFoundException;
import com.example.TicketManagement.exception.WrongStatusException;
import com.example.TicketManagement.mapper.TicketMapper;
import com.example.TicketManagement.mapper.UserMapper;
import com.example.TicketManagement.repository.TicketRepository;
import com.example.TicketManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TicketService {
   private final TicketRepository ticketRepository;
   private final UserRepository userRepository;



    public TicketResponseDTO createTicket(Long id,CreateTicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();

        ticket.setDescription(ticketRequestDTO.getDescription());
        ticket.setTitle(ticketRequestDTO.getTitle());
        ticket.setStatus(Status.OPEN);
        ticket.setUser(userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND")));
        ticket.setPriority(Priority.MEDIUM);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setModifiedAt(LocalDateTime.now());

         ticketRepository.save(ticket);

         return TicketMapper.mapD(ticket);


    }



    public Long removeTicket(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
            ticketRepository.deleteById(id);

            return id;

    }




    public TicketResponseDTO findbyId(Long id){

        Ticket ticketEntity = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();

            ticketResponseDTO.setId( ticketEntity.getId());
            ticketResponseDTO.setDescription(ticketEntity.getDescription());
            ticketResponseDTO.setPriority(ticketEntity.getPriority());
            ticketResponseDTO.setCreatedAt( ticketEntity.getCreatedAt());
            ticketResponseDTO.setModifiedAt(ticketEntity.getModifiedAt());
            ticketResponseDTO.setUserDto(UserMapper.mapU(userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"))));
            ticketResponseDTO.setStatus(ticketEntity.getStatus());


            return ticketResponseDTO;

    }




    public TicketResponseDTO ResolveIssueTicket(Long id){ //internet server error why !!!!!!!!!!

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        if(ticket.getStatus() != Status.OPEN  || ticket.getStatus() != Status.REOPENED  || ticket.getStatus() != Status.IN_PROGRESS  ){
            throw new WrongStatusException("RESOLVE issue Status Wrong");
        }
        else {ticket.setStatus(Status.RESOLVED);}

         ticketRepository.save(ticket);

         return TicketMapper.mapD(ticket);

    }


    public TicketResponseDTO closeIssueTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        if(ticket.getStatus() != Status.OPEN || ticket.getStatus() != Status.RESOLVED || ticket.getStatus() != Status.IN_PROGRESS || ticket.getStatus() != Status.REOPENED){
            throw new IdNotFoundException("CLOSE issue Status Wrong");
        }
        else ticket.setStatus(Status.CLOSED);

         ticketRepository.save(ticket);

        return TicketMapper.mapD(ticket);

    }


    public TicketResponseDTO StartProgressTicket(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
        if(ticket.getStatus() != Status.OPEN){
            throw new WrongStatusException("START Progress Status Wrong");
        }
        else {ticket.setStatus(Status.IN_PROGRESS);}

         ticketRepository.save(ticket);

        return TicketMapper.mapD(ticket);


    }

    public TicketResponseDTO StopProgressTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        if(ticket.getStatus() != Status.IN_PROGRESS){
            throw new WrongStatusException("STOP Progress Status Wrong");
        }
        else {ticket.setStatus(Status.OPEN);}

         ticketRepository.save(ticket);

        return TicketMapper.mapD(ticket);


    }


    public TicketResponseDTO ReopenIssueTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND "));
        if(ticket.getStatus() != Status.RESOLVED || ticket.getStatus() != Status.CLOSED){
            throw new WrongStatusException("REOPEN wrong Status");
        }
        else {ticket.setStatus(Status.REOPENED);}

         ticketRepository.save(ticket);

        return TicketMapper.mapD(ticket);

    }




}
