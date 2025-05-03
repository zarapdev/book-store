package az.hamburg.it.spring.task.model.response;

import az.hamburg.it.spring.task.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long id;

    private String title;
    private String author;
    private String bookCode;
    private Integer publicationYear;
    private Integer reserved_quantity;
    private Integer available_quantity;
    private Integer borrow_quantity;
    private Integer read_count;
    private Status status;
}
