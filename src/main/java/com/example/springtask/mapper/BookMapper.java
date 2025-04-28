package com.example.springtask.mapper;

import com.example.springtask.dao.entity.BookEntity;
import com.example.springtask.model.enums.Status;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookResponse;
import com.example.springtask.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static com.example.springtask.model.enums.Status.ACTIVE;

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
                status(ACTIVE)
                .build();
    }

    public void updateBook(BookRequest bookRequest, BookEntity bookEntity) {
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setPublicationYear(bookRequest.getPublicationYear());
    }

    public PageableResponse<BookResponse> BuildPageableResponse
            (Page<BookEntity> bookEntityPage) {
        return PageableResponse.<BookResponse>builder().list(bookEntityPage.
                        map(this::buildBookResponse).toList())
                .currentPageNumber(bookEntityPage.getNumber())
                .totalPage(bookEntityPage.getTotalPages())
                .totalElements(bookEntityPage.getTotalElements())
                .numberOfElement(bookEntityPage.getNumberOfElements())
                .hasNextPage(bookEntityPage.hasNext()).build();


    }
}
