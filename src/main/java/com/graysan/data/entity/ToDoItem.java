package com.graysan.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo_item")
public class ToDoItem implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long todoId;

    private String title;
    private String content;
    private boolean completed;

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + todoId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", completed=" + completed +
                '}';
    }

}
