package com.example.springtask.dao.repository;

import com.example.springtask.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity ,
        Long> {


    Optional<BookEntity> findByTitleAndPublicationYear(String title, Integer publicationYear);

}

