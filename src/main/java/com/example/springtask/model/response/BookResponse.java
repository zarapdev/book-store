package com.example.springtask.model.response;

import com.example.springtask.model.enums.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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
    private Status status;
}
