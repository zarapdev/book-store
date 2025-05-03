package az.hamburg.it.spring.task.service.abstraction;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.model.request.StudentRequest;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.model.response.StudentResponse;

public interface StudentService {

    void addStudent(StudentRequest studentRequest);

    StudentResponse getStudent(Long id);


    void updateStudent(Long id, StudentRequest studentRequest);
    void deleteUser(Long id);
    PageableResponse<StudentResponse> getAllStudents (PageCriteria pageCriteria);
}

