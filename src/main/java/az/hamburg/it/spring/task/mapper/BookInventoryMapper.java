package az.hamburg.it.spring.task.mapper;

import az.hamburg.it.spring.task.dao.entity.BookEntity;
import az.hamburg.it.spring.task.dao.entity.BookInventoryEntity;
import az.hamburg.it.spring.task.model.request.BookRequest;

import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static az.hamburg.it.spring.task.model.enums.Status.ACTIVE;

public enum BookInventoryMapper {
    BOOK_INVENTORY_MAPPER;

    public BookInventoryEntity buildInventoryEntity(BookRequest bookRequest) {
        return BookInventoryEntity.builder()
                .reserved_quantity(1)
                .available_quantity(1)
                .borrow_quantity(0)
                .read_count(0)
                .status(ACTIVE)
                .build();
    }

    public BookInventoryEntity increaseBookInventoryQuantities(BookInventoryEntity bookInventoryEntity) {
        bookInventoryEntity.setAvailable_quantity(bookInventoryEntity.getAvailable_quantity() + 1);
        bookInventoryEntity.setReserved_quantity(bookInventoryEntity.getReserved_quantity() + 1);
        return bookInventoryEntity;
    }

    public void decreaseBookInventoryQuantities(BookInventoryEntity bookInventoryEntity) {
        bookInventoryEntity.setReserved_quantity(bookInventoryEntity.getReserved_quantity() - 1);
        bookInventoryEntity.setAvailable_quantity(bookInventoryEntity.getAvailable_quantity() - 1);
    }

    public BookResponse buildInventoryResponse (BookInventoryEntity bookInventoryEntity){
        BookEntity bookEntity=bookInventoryEntity.getBooks().get(0);
        return BookMapper.BOOK_MAPPER.buildBookResponse(bookEntity);
    }

    public PageableResponse<BookResponse>
    buildPageableResponse(Page<BookInventoryEntity> bookInventoryEntityPage) {
        return PageableResponse.<BookResponse>builder()
                .list(bookInventoryEntityPage
                        .map(this::buildInventoryResponse).toList())
                .currentPageNumber(bookInventoryEntityPage.getNumber())
                .totalPage(bookInventoryEntityPage.getTotalPages())
                .totalElements(bookInventoryEntityPage.getTotalElements())
                .numberOfElement(bookInventoryEntityPage.getNumberOfElements())
                .hasNextPage(bookInventoryEntityPage.hasNext()).build();
    }

}

