package com.example.springtask.controller;


import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookInventoryResponse;
import com.example.springtask.model.response.PageableResponse;
import com.example.springtask.service.abstraction.BookInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-inventory")
public class BookInventoryController {
    private final BookInventoryService bookInventoryService;


    @PostMapping("/add")//valid limit qoyuruq ***sayini nezret edirsen password
    public void addBookInventory(@Valid @RequestBody BookRequest bookRequest) {
        bookInventoryService.addBookToInventory(bookRequest);
    }
    @GetMapping("/all")
    public PageableResponse<BookInventoryResponse> getAllInventory(PageCriteria pageCriteria){
        return bookInventoryService.getAllInventory(pageCriteria);

    }

}
