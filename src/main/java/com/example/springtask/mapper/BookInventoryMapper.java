package com.example.springtask.mapper;

import com.example.springtask.dao.entity.BookInventoryEntity;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookInventoryResponse;
import com.example.springtask.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static com.example.springtask.model.enums.Status.ACTIVE;

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
    public BookInventoryResponse buildInventoryResponse (BookInventoryEntity bookInventoryEntity){
        return BookInventoryResponse.builder().id(bookInventoryEntity.getId())
                .reserved_quantity(bookInventoryEntity.getReserved_quantity())
                .borrow_quantity(bookInventoryEntity.getBorrow_quantity())
                .status(bookInventoryEntity.getStatus())
                .available_quantity(bookInventoryEntity.getAvailable_quantity())
                .read_count(bookInventoryEntity.getRead_count()).build();
    }

    public PageableResponse<BookInventoryResponse>
    buildPageableResponse(Page<BookInventoryEntity> bookInventoryEntityPage) {
        return PageableResponse.<BookInventoryResponse>builder()
                .list(bookInventoryEntityPage
                        .map(this::buildInventoryResponse).toList())
                .currentPageNumber(bookInventoryEntityPage.getNumber())
                .totalPage(bookInventoryEntityPage.getTotalPages())
                .totalElements(bookInventoryEntityPage.getTotalElements())
                .numberOfElement(bookInventoryEntityPage.getNumberOfElements()).hasNextPage(bookInventoryEntityPage.hasNext()).build();
    }

}

