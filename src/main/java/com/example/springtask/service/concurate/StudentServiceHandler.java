package com.example.springtask.service.concurate;

import com.example.springtask.dao.entity.StudentEntity;
import com.example.springtask.exeption.NotFoundException;
import com.example.springtask.mapper.StudentMapper;
import com.example.springtask.model.request.StudentRequest;
import com.example.springtask.model.response.StudentResponse;
import com.example.springtask.dao.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceHandler {
    private final StudentRepository studentRepository;

    public void addStudent(StudentRequest studentRequest) {
        StudentEntity studentEntity=StudentMapper.STUDENT_MAPPER.buildStudentEntity(studentRequest);
        studentRepository.save(studentEntity);
    }

    public StudentResponse getStudent(Long id) {
        StudentEntity studentEntity = getEntity(id);
        return StudentMapper.STUDENT_MAPPER.buildStudentResponse(studentEntity);
    }

    private StudentEntity getEntity(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Student not found"));
    }
    // findStudentIfExist
    // "Student not found" hard code olaraq yazmyin

    public void updateStudent(Long id, StudentRequest studentRequest) {
        Optional<StudentEntity> studentEntityOptional = studentRepository.findById(id);
        StudentEntity studentEntity = studentEntityOptional.get();
        studentEntity.setName(studentRequest.getName());
        studentEntity.setSurname(studentRequest.getSurname());
        studentRepository.save(studentEntity);

    }
    // update in daxilindeki hisseni mapper a apar

    public void deleteUser(Long id) {
        studentRepository.deleteById(id);
    }

    // islediyinden eminsinizmi ?

}