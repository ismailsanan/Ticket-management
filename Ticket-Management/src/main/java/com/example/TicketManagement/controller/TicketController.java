package com.example.TicketManagement.controller;


import com.example.TicketManagement.dto.request.CreateTicketRequestDTO;
import com.example.TicketManagement.dto.response.TicketResponseDTO;
import com.example.TicketManagement.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;
//    private final UserService userService;


    @PostMapping("/{userId}")
    public TicketResponseDTO createTicket(@PathVariable Long userId, @RequestBody CreateTicketRequestDTO ticketRequestDTO){


        //        Ticket ticket = ticketService.createTicket(userId,ticketRequestDTO);
        //
        //        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        //
        //        ticketResponseDTO.setId(ticket.getId());
        //        ticketResponseDTO.setPriority(ticket.getPriority());
        //        ticketResponseDTO.setCreatedAt( ticket.getCreatedAt());
        //        ticketResponseDTO.setStatus(ticket.getStatus());
        //        ticketResponseDTO.setModifiedAt(ticket.getModifiedAt());
        //        ticketResponseDTO.setUserDto(userService.findById(userId));
        //        ticketResponseDTO.setStatus(ticket.getStatus());

        return ticketService.createTicket(userId,ticketRequestDTO);
    }


    @DeleteMapping("/{id}")
    public Long removeTicket(@PathVariable Long id){
        return ticketService.removeTicket(id);
    }



    @GetMapping("/{id}")
  public TicketResponseDTO findbyId(@PathVariable Long id){
       return ticketService.findbyId(id);

   }



   @PutMapping("/{id}/startprogress")
   public  TicketResponseDTO StartProgressTicket(@PathVariable Long id){
        return ticketService.StartProgressTicket(id);
   }

    @PutMapping("/{id}/resolveticket")
    public  TicketResponseDTO ResolveTicket(@PathVariable Long id){
        return ticketService.ResolveIssueTicket(id);
    }

    @PutMapping("/{id}/closeissue")
    public  TicketResponseDTO  closeIssueTicket(@PathVariable Long id){
        return ticketService.closeIssueTicket(id);
    }

    @PutMapping("/{id}/stoprogress")
    public  TicketResponseDTO StopProgressTicket(@PathVariable Long id){
        return  ticketService.StopProgressTicket(id);
    }


    @PutMapping("/{id}/reopenissue")
    public  TicketResponseDTO  RepoenIssueTicket(@PathVariable Long id){
        return ticketService.ReopenIssueTicket(id);
    }



}
