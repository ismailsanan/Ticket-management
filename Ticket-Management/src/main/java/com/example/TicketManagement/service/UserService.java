package com.example.TicketManagement.service;

import com.example.TicketManagement.dto.request.UserRequestDTO;
import com.example.TicketManagement.dto.response.UserResponseDTO;
import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.enumeration.Roles;
import com.example.TicketManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userrepository;

    public User createUser(UserRequestDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setRoles(Roles.CUSTOMER);
        user.setEmail(userDTO.getEmail());
        return userrepository.save(user);
    }

    public UserResponseDTO findById(Long id){
        Optional<User> user = userrepository.findById(id);

        if(user.isPresent()){
            User userE = user.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(userE.getId());
            userResponseDTO.setEmail(userE.getEmail());
            userResponseDTO.setRoles(userE.getRoles());
            userResponseDTO.setName(userE.getName());

            return userResponseDTO;
        }
        else throw new RuntimeException("user not found");


    }


}
