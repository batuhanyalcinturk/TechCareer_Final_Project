package com.graysan.business.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToDoItemDto {

    private Long id;
    private String title;
    private boolean completed;

}
