package az.hamburg.it.spring.task.service.abstraction;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.model.request.BookRequest;

import az.hamburg.it.spring.task.model.response.BookResponse;
import az.hamburg.it.spring.task.model.response.PageableResponse;

public interface BookInventoryService {
    void addBookToInventory(BookRequest bookRequest);

    PageableResponse<BookResponse> getAllInventory(PageCriteria pageCriteria);

}
