package com.example.springtask.dao.repository;

import com.example.springtask.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <StudentEntity, Long> {

}
