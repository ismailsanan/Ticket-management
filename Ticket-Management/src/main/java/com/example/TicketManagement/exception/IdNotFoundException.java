package com.example.TicketManagement.exception;

public class IdNotFoundException extends  RuntimeException{

    public IdNotFoundException(String m){
        super(m);
    }
}
