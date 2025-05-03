package az.hamburg.it.spring.task.controller;


import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.model.request.BookRequest;

import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.service.abstraction.BookInventoryService;
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
    public PageableResponse<BookResponse> getAllInventory(PageCriteria pageCriteria){
        return bookInventoryService.getAllInventory(pageCriteria);

    }

}
