package com.example.TicketManagement.mapper;

import com.example.TicketManagement.dto.response.UserResponseDTO;
import com.example.TicketManagement.entity.User;

import java.util.function.Function;

public class UserMapper {


    private static final Function<User, UserResponseDTO>  mapU =
            user ->  new UserResponseDTO(user.getId() ,user.getName() , user.getEmail() , user.getRoles() , user.getPassword());


    public static UserResponseDTO mapU (User users){
        return mapU.apply(users);
    }
    private static  final Function<UserResponseDTO, User> mapD =
            userResponseDTO  -> new User(userResponseDTO.getId(),userResponseDTO.getName() , userResponseDTO.getEmail() , userResponseDTO.getRoles(),userResponseDTO.getPassword());

    public static User mapD(UserResponseDTO userResponseDTO){
        return mapD.apply(userResponseDTO);
    }


}
