package com.example.TicketManagement.dto.response;

import com.example.TicketManagement.enumeration.Priority;
import com.example.TicketManagement.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor

public class TicketResponseDTO {

        private Long id;
        private UserResponseDTO userDto;
        private Status status;
        private Priority priority;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private  LocalDateTime modifiedAt;



}
