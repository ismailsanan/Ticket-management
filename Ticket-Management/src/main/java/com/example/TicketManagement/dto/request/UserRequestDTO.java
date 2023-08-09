package com.example.TicketManagement.dto.request;

import com.example.TicketManagement.enumeration.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private String email;
    private Roles role;
    private String password;


}
