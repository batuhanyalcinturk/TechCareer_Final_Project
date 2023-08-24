package com.graysan.data.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String fullName;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<ToDoItem> todos;
}
