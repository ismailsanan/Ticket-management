package com.example.TicketManagement.dto.request;


import com.example.TicketManagement.enumeration.Roles;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequestDTO {



    private String email;
    private String password;

}
