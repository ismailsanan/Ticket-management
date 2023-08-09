package com.example.TicketManagement.service;

import com.example.TicketManagement.dto.request.AuthenticateRequestDTO;
import com.example.TicketManagement.dto.request.RegisterRequestDTO;
import com.example.TicketManagement.dto.request.UserRequestDTO;
import com.example.TicketManagement.dto.response.AuthenticationResponseDTO;
import com.example.TicketManagement.dto.response.UserResponseDTO;
import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.enumeration.Roles;
import com.example.TicketManagement.repository.UserRepository;
import com.example.TicketManagement.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userrepository;
    private final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){

        var user = User.builder()
                .name(userRequestDTO.getName())
                .role(userRequestDTO.getRole())
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .build();

       User user2 =  userrepository.save(user);
       var response = UserResponseDTO.builder()
               .id(user2.getId())
               .name(user2.getName())
               .role(user2.getRole())
               .email(user2.getEmail())
               .build();


        return response;
    }

    public UserResponseDTO findById(Long id){
        Optional<User> user = userrepository.findById(id);

        if(user.isPresent()){
            User userE = user.get();
            return  UserResponseDTO.builder()
                    .id(userE.getId())
                    .email(userE.getEmail())
                    .role(userE.getRole())
                    .name(userE.getName())
                    .build();


        }
        else {throw new RuntimeException("user not found");}


    }


    public AuthenticationResponseDTO signup(RegisterRequestDTO authenticateRequest){

        var user = User.builder()
                .name(authenticateRequest.getName())
                .email(authenticateRequest.getEmail())
                .password(passwordEncoder.encode(authenticateRequest.getPassword()))
                .role(Roles.CUSTOMER)
                .build();

        userrepository.save(user);



        var jwtToken = jwtService.generateToken(user.getUsername());

        return AuthenticationResponseDTO.builder()
                .token(jwtToken).build();
    }


    public AuthenticationResponseDTO login(AuthenticateRequestDTO authenticateRequest){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(), authenticateRequest.getPassword()));

                final User user = userrepository.findByEmail(authenticateRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

               String jwtToken = jwtService.generateToken(user.getUsername());

               return new AuthenticationResponseDTO(jwtToken);



    }





}
