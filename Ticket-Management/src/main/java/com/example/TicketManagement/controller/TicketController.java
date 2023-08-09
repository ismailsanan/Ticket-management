package com.example.TicketManagement.controller;


import com.example.TicketManagement.dto.request.CreateTicketRequestDTO;
import com.example.TicketManagement.dto.response.TicketResponseDTO;
import com.example.TicketManagement.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/createticket/{userId}")
    public TicketResponseDTO createTicket(@PathVariable Long userId, @RequestBody CreateTicketRequestDTO ticketRequestDTO){


        return ticketService.createTicket(userId,ticketRequestDTO);
    }

    @PreAuthorize("hasAuthority('EXPERT')")
    @DeleteMapping("/removeticket/{id}")
    public Long removeTicket(@PathVariable Long id){
        return ticketService.removeTicket(id);
    }



    @GetMapping("geticket/{id}")
  public TicketResponseDTO findbyId(@PathVariable Long id){
       return ticketService.findbyId(id);

   }

   @PreAuthorize("hasAuthority('MANAGER')")
   @PostMapping("/{ticketid}/assign/{expertid}/{managerid}")
   public String assignTicket(@PathVariable Long ticketid, @PathVariable Long expertid , @PathVariable Long managerid){

             return ticketService.assignTicket(ticketid , expertid , managerid);

   }


    @PreAuthorize("hasAuthority('EXPERT')")
   @PutMapping("/process/{id}/startprogress")
   public  TicketResponseDTO StartProgressTicket(@PathVariable Long id){
        return ticketService.StartProgressTicket(id);
   }
    @PreAuthorize("hasAuthority('EXPERT')")
    @PutMapping("/process/{id}/resolveticket")
    public  TicketResponseDTO ResolveTicket(@PathVariable Long id){
        return ticketService.ResolveIssueTicket(id);
    }
    @PreAuthorize("hasAuthority('EXPERT')")
    @PutMapping("/process/{id}/closeissue")
    public  TicketResponseDTO  closeIssueTicket(@PathVariable Long id){
        return ticketService.closeIssueTicket(id);
    }
    @PreAuthorize("hasAuthority('EXPERT')")
    @PutMapping("/process/{id}/stoprogress")
    public  TicketResponseDTO StopProgressTicket(@PathVariable Long id){
        return  ticketService.StopProgressTicket(id);
    }

    @PreAuthorize("hasAuthority('EXPERT')")
    @PutMapping("/process/{id}/reopenissue")
    public  TicketResponseDTO  ReopenIssueTicket(@PathVariable Long id){
        return ticketService.ReopenIssueTicket(id);
    }



}
