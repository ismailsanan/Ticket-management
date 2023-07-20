package com.example.TicketManagement.controller;

import com.example.TicketManagement.dto.request.UserRequestDTO;
import com.example.TicketManagement.dto.response.UserResponseDTO;
import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userdto){

       User user =  userService.createUser(userdto);
       UserResponseDTO userResponseDTO = new UserResponseDTO();
       userResponseDTO.setName(user.getName());
       userResponseDTO.setEmail(user.getEmail());
       userResponseDTO.setId(user.getId());
       userResponseDTO.setRoles(user.getRoles());

        return userResponseDTO;
    }



    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return userService.findById(id);

    }


}
