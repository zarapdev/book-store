package az.hamburg.it.spring.task.service.concrete;

import az.hamburg.it.spring.task.criteria.PageCriteria;
import az.hamburg.it.spring.task.dao.entity.StudentEntity;
import az.hamburg.it.spring.task.exeption.NotFoundException;
import az.hamburg.it.spring.task.model.request.StudentRequest;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.model.response.StudentResponse;
import az.hamburg.it.spring.task.dao.repository.StudentRepository;
import az.hamburg.it.spring.task.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static az.hamburg.it.spring.task.exeption.ErrorMessage.STUDENT_NOT_FOUND;
import static az.hamburg.it.spring.task.mapper.StudentMapper.STUDENT_MAPPER;

@Service
@RequiredArgsConstructor
public class StudentServiceHandler implements StudentService {
    private final StudentRepository studentRepository;

    public void addStudent(StudentRequest studentRequest) {
        StudentEntity studentEntity = STUDENT_MAPPER.buildStudentEntity(studentRequest);
        studentRepository.save(studentEntity);
    }

    public StudentResponse getStudent(Long id) {
        StudentEntity studentEntity = findStudentById(id);
        return STUDENT_MAPPER.buildStudentResponse(studentEntity);
    }

    private StudentEntity findStudentById(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND.getMessage()));
    }
    // findStudentIfExist
    // "Student not found" hard code olaraq yazmyin

    public void updateStudent(Long id, StudentRequest studentRequest) {

        StudentEntity studentEntity = findStudentById(id);
        STUDENT_MAPPER.updateStudent(studentEntity, studentRequest);
        studentRepository.save(studentEntity);

    }
    // update in daxilindeki hisseni mapper a apar

    public void deleteUser(Long id) {
        studentRepository.deleteById(id);
    }

    // islediyinden eminsinizmi ?
    public PageableResponse<StudentResponse> getAllStudents(PageCriteria pageCriteria) {
        Page<StudentEntity> studentPage = studentRepository.
                findAll(PageRequest
                        .of(pageCriteria.getPage(), pageCriteria.getCount()));
        return STUDENT_MAPPER.buildPageableResponse(studentPage);
    }

}