package com.graysan.business.dto;

import lombok.Data;

@Data
public class ToDoItemDto {

    private Long id;
    private String title;
    private boolean completed;

}
