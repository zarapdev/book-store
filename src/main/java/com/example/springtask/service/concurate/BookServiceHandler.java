package com.example.springtask.service.concurate;

import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.dao.entity.BookEntity;
import com.example.springtask.dao.entity.BookInventoryEntity;
import com.example.springtask.exeption.NotFoundException;
import com.example.springtask.mapper.BookInventoryMapper;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookResponse;
import com.example.springtask.dao.repository.BookRepository;
import com.example.springtask.model.response.PageableResponse;
import com.example.springtask.service.abstraction.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.springtask.exeption.ErrorMessage.BOOK_NOT_FOUND;
import static com.example.springtask.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookServiceHandler implements BookService {
    private final BookRepository bookRepository;

    @Override
    public PageableResponse<BookResponse> getAllBooks(PageCriteria pageCriteria) {
        Page<BookEntity> page = bookRepository.findAll(PageRequest
                .of(pageCriteria.getPage(), pageCriteria.getCount()));
        return BOOK_MAPPER.BuildPageableResponse(page);

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
        BookInventoryMapper.BOOK_INVENTORY_MAPPER.decreaseBookInventoryQuantities(bookEntity.getBookInventory());
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
    // bu method niye elave edilib ?

}

// Optional [ private final  FieldDefaults istifade ede bilersiniz bunlar ucun]
// Log elave et  (Slf4j for example just use info and error)
// NotfoundException   f boyuk herfle
// BOOK_NOT_FOUND.getMessage()   istesen getCode da elave ede bilersen Front ucun
// BookEntity bookEntity = findBookEntity(id); var istifade ede bilersiniz BookEntity evezine
// bookRepository.delete(bookEntity); kitabi bazadan birdefelik niye silirsiniz ? soft delete istifade niye etmirsiniz
// niye delete de deleteById yox
