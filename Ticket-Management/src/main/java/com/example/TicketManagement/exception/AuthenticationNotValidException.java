package com.example.TicketManagement.exception;

public class AuthenticationNotValidException extends RuntimeException{

    public AuthenticationNotValidException(String m){
        super(m);
    }
}
