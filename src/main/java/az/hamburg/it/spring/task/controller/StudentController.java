package az.hamburg.it.spring.task.controller;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.model.request.StudentRequest;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.model.response.StudentResponse;
import az.hamburg.it.spring.task.service.concrete.StudentServiceHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceHandler studentService;

    @PostMapping("/add")
    public void addStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.addStudent(studentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        studentService.deleteUser(id);
    }

    @GetMapping("/all")
    public PageableResponse<StudentResponse> getAllBooks(PageCriteria pageCriteria) {
        return studentService.getAllStudents(pageCriteria);
    }

}



