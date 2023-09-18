package com.example.TicketManagement;

import com.example.TicketManagement.entity.User;
import com.example.TicketManagement.enumeration.Roles;
import com.example.TicketManagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TicketManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository , PasswordEncoder passwordEncoder){
		User user = new User(1L , "man" , "email@email.com" , Roles.MANAGER , passwordEncoder.encode("password") );
		return args -> {userRepository.save(user);};
	}
}
