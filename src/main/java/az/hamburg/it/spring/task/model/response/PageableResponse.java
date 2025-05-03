package az.hamburg.it.spring.task.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableResponse<T>{
    private List<T> list;
    private Integer currentPageNumber;
    private Integer totalPage;
    private Integer numberOfElement;
    private Long totalElements;
    private boolean hasNextPage;

}
