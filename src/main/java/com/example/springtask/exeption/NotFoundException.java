package com.example.springtask.exeption;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

}
