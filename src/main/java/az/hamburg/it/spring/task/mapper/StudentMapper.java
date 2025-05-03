package az.hamburg.it.spring.task.mapper;

import az.hamburg.it.spring.task.dao.entity.StudentEntity;
import az.hamburg.it.spring.task.model.request.StudentRequest;
import az.hamburg.it.spring.task.model.response.PageableResponse;
import az.hamburg.it.spring.task.model.response.StudentResponse;
import org.springframework.data.domain.Page;

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
    public PageableResponse<StudentResponse> buildPageableResponse
            (Page<StudentEntity> studentEntityPage) {
        return PageableResponse.<StudentResponse>builder().list(studentEntityPage.
                        map(this::buildStudentResponse).toList())
                .currentPageNumber(studentEntityPage.getNumber())
                .totalPage(studentEntityPage.getTotalPages())
                .totalElements(studentEntityPage.getTotalElements())
                .numberOfElement(studentEntityPage.getNumberOfElements())
                .hasNextPage(studentEntityPage.hasNext()).build();



    }
    public void updateStudent (StudentEntity studentEntity,StudentRequest  studentRequest){
        studentEntity.setName(studentRequest.getName());
        studentEntity.setSurname(studentRequest.getSurname());
        studentEntity.setAge(studentEntity.getAge());
    }
}

