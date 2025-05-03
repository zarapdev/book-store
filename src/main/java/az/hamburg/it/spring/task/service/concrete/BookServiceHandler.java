package az.hamburg.it.spring.task.service.concrete;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.dao.entity.BookEntity;
import az.hamburg.it.spring.task.dao.entity.BookInventoryEntity;
import az.hamburg.it.spring.task.dao.repository.BookRepository;
import az.hamburg.it.spring.task.exeption.NotFoundException;
import az.hamburg.it.spring.task.model.request.BookRequest;
import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.service.abstraction.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static az.hamburg.it.spring.task.exeption.ErrorMessage.BOOK_NOT_FOUND;
import static az.hamburg.it.spring.task.mapper.BookInventoryMapper.BOOK_INVENTORY_MAPPER;
import static az.hamburg.it.spring.task.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookServiceHandler implements BookService {
    private final BookRepository bookRepository;

    @Override
    public PageableResponse<BookResponse> getAllBooks(PageCriteria pageCriteria) {
        Page<BookEntity> page = bookRepository.findAll(PageRequest
                .of(pageCriteria.getPage(), pageCriteria.getCount()));
        return BOOK_MAPPER.buildPageableResponse(page);
    }

    @Override
    public void addBook(BookRequest bookRequest, BookInventoryEntity bookInventoryEntity) {
        BookEntity bookEntity = BOOK_MAPPER.buildBookEntity(bookRequest);
        bookEntity.setBookInventory(bookInventoryEntity);
        bookRepository.save(bookEntity);
    }

    @Override
    public BookResponse getBook(Long id) {
        BookEntity bookEntity = findBookEntity(id);
        return BOOK_MAPPER.buildBookResponse(bookEntity);
    }

    @Override
    public void updateBook(Long id, BookRequest bookRequest) {
        BookEntity bookEntity = findBookEntity(id);
        BOOK_MAPPER.updateBook(bookRequest, bookEntity);
        bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBooks(Long id) {
        BookEntity bookEntity = findBookEntity(id);
        BOOK_INVENTORY_MAPPER.decreaseBookInventoryQuantities(bookEntity.getBookInventory());
        bookRepository.deleteById(id);
    }

    private BookEntity findBookEntity(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(BOOK_NOT_FOUND.getMessage())
        );
    }

    @Override
    public Optional<BookInventoryEntity> findInventoryByBookDetails(String title,
                                                                    Integer publicationYear) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findByTitleAndPublicationYear(title,
                publicationYear);
        return bookEntityOptional.map(bookEntity -> bookEntity.getBookInventory());

    }

}
