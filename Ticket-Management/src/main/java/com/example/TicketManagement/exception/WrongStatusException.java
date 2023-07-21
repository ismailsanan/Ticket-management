package com.example.TicketManagement.exception;

public class WrongStatusException extends  RuntimeException{

    public WrongStatusException(String m){
        super(m);
    }
}
