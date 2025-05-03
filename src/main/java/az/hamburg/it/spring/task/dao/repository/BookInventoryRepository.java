package az.hamburg.it.spring.task.dao.repository;

import az.hamburg.it.spring.task.dao.entity.BookInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryRepository extends JpaRepository<BookInventoryEntity, Long> {
}
