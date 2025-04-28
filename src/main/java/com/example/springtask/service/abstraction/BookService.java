package com.example.springtask.service.abstraction;

import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.dao.entity.BookInventoryEntity;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookResponse;
import com.example.springtask.model.response.PageableResponse;

import java.util.Optional;

public interface BookService {
    void addBook(BookRequest bookRequest, BookInventoryEntity bookInventoryEntity);
    BookResponse getBook(Long id);
    void updateBook(Long id, BookRequest bookRequest);
    void deleteBooks(Long id);
    Optional<BookInventoryEntity>
    findInventoryByBookDetails(String title, Integer publicationYear);
    PageableResponse <BookResponse> getAllBooks(PageCriteria pageCriteria);
    }


