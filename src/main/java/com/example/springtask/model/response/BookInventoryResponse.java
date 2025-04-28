package com.example.springtask.model.response;


import com.example.springtask.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookInventoryResponse {
    private Long id;

    private Integer reserved_quantity;
    private Integer available_quantity;
    private Integer borrow_quantity;
    private Integer read_count;
    private Status status;

}
