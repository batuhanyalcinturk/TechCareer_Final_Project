package com.graysan.business.dto;

import java.io.Serializable;

public class ToDoItemDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String title;
    private String content;
    private boolean completed;

}
