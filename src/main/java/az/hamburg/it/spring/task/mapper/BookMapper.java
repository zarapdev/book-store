package az.hamburg.it.spring.task.mapper;

import az.hamburg.it.spring.task.dao.entity.BookEntity;
import az.hamburg.it.spring.task.model.request.BookRequest;
import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static az.hamburg.it.spring.task.model.enums.Status.ACTIVE;

public enum BookMapper {
    BOOK_MAPPER;

    public BookEntity buildBookEntity(BookRequest bookRequest) {
        return BookEntity.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .bookCode(bookRequest.getBookCode())
                .publicationYear(bookRequest.getPublicationYear())
                .status(ACTIVE)
                .build();

    }

    public BookResponse buildBookResponse(BookEntity bookEntity) {
        return BookResponse.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .bookCode(bookEntity.getBookCode())
                .publicationYear(bookEntity.getPublicationYear()).
                status(bookEntity.getStatus())
                .reserved_quantity(bookEntity.getBookInventory().getReserved_quantity())
                .available_quantity(bookEntity.getBookInventory().getAvailable_quantity())
                .borrow_quantity(bookEntity.getBookInventory().getBorrow_quantity())
                .read_count(bookEntity.getBookInventory().getRead_count())
                .build();
    }

    public void updateBook(BookRequest bookRequest, BookEntity bookEntity) {
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setPublicationYear(bookRequest.getPublicationYear());
    }

    public PageableResponse<BookResponse> buildPageableResponse(Page<BookEntity> bookEntityPage) {
        return PageableResponse.<BookResponse>builder()
                .list(bookEntityPage.
                        map(this::buildBookResponse).toList())
                .currentPageNumber(bookEntityPage.getNumber())
                .totalPage(bookEntityPage.getTotalPages())
                .totalElements(bookEntityPage.getTotalElements())
                .numberOfElement(bookEntityPage.getNumberOfElements())
                .hasNextPage(bookEntityPage.hasNext()).build();

    }
}
