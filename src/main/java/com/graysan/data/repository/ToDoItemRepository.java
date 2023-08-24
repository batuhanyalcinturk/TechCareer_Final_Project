package com.graysan.data.repository;

import com.graysan.data.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    int countAllByCompleted(boolean completed);

    List<ToDoItem> findAllByCompleted(boolean completed);
}
