package com.example.springtask.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandle {
    @ResponseStatus(HttpStatus.NOT_FOUND) // fronta bu mesaji otureceyik
    @ExceptionHandler(NotFoundException.class) //error exeptionu geri qaytarir
    public ErrorResponse handle(NotFoundException notfoundException) {
        return new ErrorResponse(notfoundException.getMessage());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ErrorResponse(methodArgumentNotValidException.getMessage());

    }
}

// static import zehmet olmasa
// Exception ucunde ErrorHandler et