package com.example.springtask.service.concurate;

import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.dao.entity.BookInventoryEntity;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.dao.repository.BookInventoryRepository;
import com.example.springtask.model.response.BookInventoryResponse;
import com.example.springtask.model.response.PageableResponse;
import com.example.springtask.service.abstraction.BookInventoryService;
import com.example.springtask.service.abstraction.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.example.springtask.mapper.BookInventoryMapper.BOOK_INVENTORY_MAPPER;

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
    public PageableResponse<BookInventoryResponse> getAllInventory(PageCriteria pageCriteria) {
        Page<BookInventoryEntity>inventoryPage=bookInventoryRepository
                .findAll(PageRequest.of(pageCriteria.getPage(),pageCriteria.getCount()));
        return BOOK_INVENTORY_MAPPER.buildPageableResponse(inventoryPage);
    }
}


// service package ni 2 package e ayir. for example abstraction and handler ve daha sonra interface de
// yarat ve class onu implementasiya etsin. Dependency inversion-u pozma

//  One teref Bookİnventory dirse demeli sizin Book sayiniz bir nece dene ola bile. Niyə görə BookService daxilində kitabı add edərkən hansı inventory e
// edeceyinizi mueyyen ederek BookRequest daxilinde inventoryId saxlamirsiniz ? ki daha sonra da inventory bazasindan cekesiniz varsa elave edesiniz ora

//

