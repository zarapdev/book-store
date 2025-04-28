package com.example.springtask.mapper;

import com.example.springtask.dao.entity.StudentEntity;
import com.example.springtask.model.request.StudentRequest;
import com.example.springtask.model.response.StudentResponse;

public enum StudentMapper {
    STUDENT_MAPPER;


    public StudentResponse buildStudentResponse(StudentEntity studentEntity) {
        return StudentResponse.builder()
                .name(studentEntity.getName())
                .surname(studentEntity.getSurname())
                .age(studentEntity.getAge())
                .build();
    }

    public StudentEntity buildStudentEntity(StudentRequest studentRequest) {
        return StudentEntity.builder().name(studentRequest.getName()).
                surname(studentRequest.getSurname()).age(studentRequest.getAge()).build();
    }
}

