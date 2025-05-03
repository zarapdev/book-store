package az.hamburg.it.spring.task.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.hamburg.it.spring.task.model.ValidationConstant.FIELD_CANNOT_BE_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String title;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String author;

    private String bookCode;

    private Integer publicationYear;
}
