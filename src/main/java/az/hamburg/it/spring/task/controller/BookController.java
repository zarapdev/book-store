package az.hamburg.it.spring.task.controller;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.model.request.BookRequest;
import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.service.abstraction.BookService;
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

