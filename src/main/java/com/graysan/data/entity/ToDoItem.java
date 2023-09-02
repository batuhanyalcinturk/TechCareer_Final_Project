package com.graysan.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;


//@Data
@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "todo_item")
public class ToDoItem implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    // ToDo öğesinin başlığını temsil eden alan.
    // Field representing the title of the ToDo item.
    private String title;

    // ToDo öğesinin tamamlanma durumunu temsil eden alan.
    // Field representing the completion status of the ToDo item.
    private boolean completed = Boolean.FALSE;
}
