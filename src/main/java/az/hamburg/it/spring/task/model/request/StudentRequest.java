package az.hamburg.it.spring.task.model.request;

import az.hamburg.it.spring.task.model.ValidationConstant;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.hamburg.it.spring.task.model.ValidationConstant.FIELD_CANNOT_BE_NULL;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String name;
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String surname;
    @Min(value = 0,message = ValidationConstant.AGE_MIN_VALUE)
    @Max(value = 150, message = ValidationConstant.AGE_MAX_VALUE)
    private int age;
}
