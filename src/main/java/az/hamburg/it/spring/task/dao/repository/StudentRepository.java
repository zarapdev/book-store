package az.hamburg.it.spring.task.dao.repository;

import az.hamburg.it.spring.task.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
