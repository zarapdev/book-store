package az.hamburg.it.spring.task.dao.repository;

import az.hamburg.it.spring.task.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity , Long> {

    Optional<BookEntity> findByTitleAndPublicationYear(String title, Integer publicationYear);

}
