package az.hamburg.it.spring.task.service.concrete;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.dao.entity.BookInventoryEntity;
import az.hamburg.it.spring.task.model.request.BookRequest;
import az.hamburg.it.spring.task.dao.repository.BookInventoryRepository;

import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.service.abstraction.BookInventoryService;
import az.hamburg.it.spring.task.service.abstraction.BookService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.hamburg.it.spring.task.mapper.BookInventoryMapper.BOOK_INVENTORY_MAPPER;

@Service
@RequiredArgsConstructor
public class BookInventoryServiceHandler implements BookInventoryService {

    private final BookInventoryRepository bookInventoryRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public void addBookToInventory(BookRequest bookRequest) {


        BookInventoryEntity bookInventoryEntity = bookService.findInventoryByBookDetails(bookRequest.getTitle(),
                        bookRequest.getPublicationYear()).
                map(beenBookInventory -> {
                    return BOOK_INVENTORY_MAPPER.increaseBookInventoryQuantities(beenBookInventory);
                })
                .orElseGet(() -> {
                    BookInventoryEntity newBookInventory = BOOK_INVENTORY_MAPPER.buildInventoryEntity(bookRequest);
                    bookInventoryRepository.save(newBookInventory);
                    return newBookInventory;

                });
        bookService.addBook(bookRequest, bookInventoryEntity);


    }

    @Override
    public PageableResponse<BookResponse> getAllInventory(PageCriteria pageCriteria) {
        Page<BookInventoryEntity> inventoryPage = bookInventoryRepository
                .findAll(PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount()));
        return BOOK_INVENTORY_MAPPER.buildPageableResponse(inventoryPage);
    }
}
