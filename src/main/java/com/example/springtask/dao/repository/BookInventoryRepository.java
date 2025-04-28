package com.example.springtask.dao.repository;

import com.example.springtask.dao.entity.BookInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryRepository extends JpaRepository <BookInventoryEntity, Long>{
    }
