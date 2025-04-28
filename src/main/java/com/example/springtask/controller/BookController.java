package com.example.springtask.controller;

import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookResponse;
import com.example.springtask.model.response.PageableResponse;
import com.example.springtask.service.abstraction.BookService;
import com.example.springtask.service.concurate.BookInventoryServiceHandler;
import com.example.springtask.service.concurate.BookServiceHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBooks(id);
    }

    @GetMapping("/all")
    public PageableResponse<BookResponse> getAllBooks(PageCriteria pageCriteria) {
        return bookService.getAllBooks(pageCriteria);

    }


}

