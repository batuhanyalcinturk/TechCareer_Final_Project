package com.graysan.data.repository;

import com.graysan.data.entity.ToDoItem;
import com.graysan.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
