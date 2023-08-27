package com.graysan.business.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToDoItemDto {

    private String title;
    private String content;
    private boolean completed;

}
