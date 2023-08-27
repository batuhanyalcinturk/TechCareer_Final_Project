package com.graysan.data.repository;

import com.graysan.data.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    int countAllByCompleted(boolean completed);

    List<ToDoItem> findAllByCompleted(boolean completed);
}
