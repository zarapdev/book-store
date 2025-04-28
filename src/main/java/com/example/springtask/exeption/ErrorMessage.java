package com.example.springtask.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    BOOK_NOT_FOUND("book not found");
    private final String message;
}
