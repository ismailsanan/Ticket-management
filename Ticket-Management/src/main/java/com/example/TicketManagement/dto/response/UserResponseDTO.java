package com.example.TicketManagement.dto.response;

import com.example.TicketManagement.enumeration.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Roles roles;
    private String password;
}
