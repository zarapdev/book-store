package az.hamburg.it.spring.task.service.abstraction;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.dao.entity.BookInventoryEntity;
import az.hamburg.it.spring.task.model.request.BookRequest;
import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;

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


