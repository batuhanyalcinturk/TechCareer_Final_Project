package com.graysan.business.dto;

import java.io.Serializable;

public class ToDoItemDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Long todoId;
    private String title;
    private boolean completed;

}
