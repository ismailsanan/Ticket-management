package com.example.TicketManagement.service;


import com.example.TicketManagement.dto.request.CreateTicketRequestDTO;
import com.example.TicketManagement.dto.response.TicketResponseDTO;
import com.example.TicketManagement.entity.Ticket;
import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.enumeration.Priority;
import com.example.TicketManagement.enumeration.Roles;
import com.example.TicketManagement.enumeration.Status;
import com.example.TicketManagement.exception.IdNotFoundException;
import com.example.TicketManagement.exception.WrongStatusException;
import com.example.TicketManagement.mapper.ChatMapper;
import com.example.TicketManagement.mapper.TicketMapper;
import com.example.TicketManagement.mapper.UserMapper;
import com.example.TicketManagement.repository.ChatRepository;
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
   private final ChatService chatService;
   private final ChatRepository chatRepository;




    public TicketResponseDTO createTicket(Long id,CreateTicketRequestDTO createTicketRequestDTO) {

        User user = userRepository.findById(id).orElseThrow(()-> new IdNotFoundException("ID NOT FOUND"));
        var chat = chatService.createChat();

        var ticket = Ticket.builder()
                .title(createTicketRequestDTO.getTitle())
                .status(Status.OPEN)
                .user(user)
                .priority(Priority.LOW)
                .chat(chat)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

         chatRepository.save(chat);
         ticketRepository.save(ticket);

//        ChatTicketResponseDTO chatTicketResponseDTO = ChatTicketResponseDTO.builder()
//                .customer(user.getName())
//                .expert(null)
//                .id(chat.getId())
//                .title(createTicketRequestDTO.getTitle())
//                .messages(null)
//                .build();
//        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
//                .id(user.getId())
//                .name(user.getName())
//                .role(user.getRole())
//                .email(user.getEmail())
//                .build();
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
            ticketResponseDTO.setChat(ChatMapper.mapE(ticketEntity.getChat()));
            ticketResponseDTO.setCreatedAt( ticketEntity.getCreatedAt());
            ticketResponseDTO.setModifiedAt(ticketEntity.getModifiedAt());
            ticketResponseDTO.setUserDto(UserMapper.mapU(userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"))));
            ticketResponseDTO.setStatus(ticketEntity.getStatus());


            return ticketResponseDTO;

    }




    public TicketResponseDTO ResolveIssueTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        System.out.println(ticket.getStatus());
        if(ticket.getStatus() != Status.OPEN  && ticket.getStatus() != Status.REOPENED  && ticket.getStatus() != Status.IN_PROGRESS  ){

            throw new WrongStatusException("RESOLVE issue Status Wrong");

        }
        else {

            ticket.setStatus(Status.RESOLVED);
            ticketRepository.save(ticket);
            }

         return TicketMapper.mapD(ticket);

    }


    public TicketResponseDTO closeIssueTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        if(ticket.getStatus() != Status.OPEN && ticket.getStatus() != Status.RESOLVED && ticket.getStatus() != Status.IN_PROGRESS && ticket.getStatus() != Status.REOPENED){
            throw new IdNotFoundException("CLOSE issue Status Wrong");
        }
        else {

            ticket.setStatus(Status.CLOSED);
            ticketRepository.save(ticket);

        }
        return TicketMapper.mapD(ticket);

    }


    public TicketResponseDTO StartProgressTicket(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
        if(ticket.getStatus() != Status.OPEN && ticket.getStatus() != Status.REOPENED){
            throw new WrongStatusException("START Progress Status Wrong");
        }
        else {ticket.setStatus(Status.IN_PROGRESS);

         ticketRepository.save(ticket);
        }

        return TicketMapper.mapD(ticket);


    }

    public TicketResponseDTO StopProgressTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND"));
        if(ticket.getStatus() != Status.IN_PROGRESS){
            throw new WrongStatusException("STOP Progress Status Wrong");
        }
        else {
            ticket.setStatus(Status.CLOSED);
            ticketRepository.save(ticket);
        }
        return TicketMapper.mapD(ticket);


    }


    public TicketResponseDTO ReopenIssueTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID NOT FOUND "));
        if(ticket.getStatus() != Status.CLOSED && ticket.getStatus() != Status.RESOLVED ){
            throw new WrongStatusException("REOPEN wrong Status");
        }
        else {
                ticket.setStatus(Status.REOPENED);
                ticketRepository.save(ticket);
            }

        return TicketMapper.mapD(ticket);

    }


    public String assignTicket(Long ticketId, Long expertid , Long managerid) {

        Ticket ticket  = ticketRepository.findById(ticketId).orElseThrow(() -> new IdNotFoundException("Ticekt id not found"));
        User manager = userRepository.findById(managerid).orElseThrow(() -> new IdNotFoundException("Manager id not found"));
        User expert  = userRepository.findById(expertid).orElseThrow(()-> new IdNotFoundException("User id not found"));

        if(expert.getRole() != Roles.EXPERT || manager.getRole() != Roles.MANAGER){
            throw new IllegalArgumentException("User not an expert");
        }

        ticket.setExpert(expert);
        ticketRepository.save(ticket);

        return "OK";

    }
}
