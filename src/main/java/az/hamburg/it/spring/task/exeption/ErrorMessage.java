package az.hamburg.it.spring.task.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    BOOK_NOT_FOUND("book not found"),
    UNEXPECTED_EXCEPTION("Unexpected exception  occurred"),
    STUDENT_NOT_FOUND("student not found");

    private final String message;

}
