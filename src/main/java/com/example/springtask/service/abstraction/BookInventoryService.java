package com.example.springtask.service.abstraction;

import com.example.springtask.criteria.PageCriteria;
import com.example.springtask.model.request.BookRequest;
import com.example.springtask.model.response.BookInventoryResponse;
import com.example.springtask.model.response.PageableResponse;

public interface BookInventoryService {
    void addBookToInventory(BookRequest bookRequest);

    PageableResponse<BookInventoryResponse> getAllInventory(PageCriteria pageCriteria);

}
