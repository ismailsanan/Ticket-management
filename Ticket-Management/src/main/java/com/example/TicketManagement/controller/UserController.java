package com.example.TicketManagement.controller;

import com.example.TicketManagement.dto.request.AuthenticateRequestDTO;
import com.example.TicketManagement.dto.request.RegisterRequestDTO;
import com.example.TicketManagement.dto.request.UserRequestDTO;
import com.example.TicketManagement.dto.response.AuthenticationResponseDTO;
import com.example.TicketManagement.dto.response.UserResponseDTO;
import com.example.TicketManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public AuthenticationResponseDTO signup( @RequestBody RegisterRequestDTO authenticateRequest){

        return userService.signup(authenticateRequest);
    }





    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/createuser")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception {

        return userService.createUser(userRequestDTO);


    }


    @PostMapping("/login")
    public AuthenticationResponseDTO login(@RequestBody AuthenticateRequestDTO authenticateRequestDTO){
        return userService.login(authenticateRequestDTO);

    }


    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return userService.findById(id);

    }


}
