package com.example.TicketManagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongStatusException.class)
    public ResponseEntity<String> handleWrongStatusException(WrongStatusException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> IdNotFoundException(IdNotFoundException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationNotValidException.class)
    public ResponseEntity<String> AuthenticationNotValidException(AuthenticationNotValidException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

}
