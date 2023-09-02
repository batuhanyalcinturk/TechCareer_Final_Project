package com.graysan.business.dto;

import lombok.Data;

// Bu sınıf, ToDo öğelerini veri taşıma amacıyla kullanılan bir DTO (Veri Transfer Nesnesi) sınıfını temsil eder.
// This class represents a DTO (Data Transfer Object) used for transporting ToDo items.
@Data
public class ToDoItemDto {
    // ToDo öğesinin benzersiz kimliğini temsil eden alan.
    // Field representing the unique identifier of the ToDo item.
    private Long id;

    // ToDo öğesinin başlığını temsil eden alan.
    // Field representing the title of the ToDo item.
    private String title;

    // ToDo öğesinin tamamlanma durumunu temsil eden alan.
    // Field representing the completion status of the ToDo item.
    private boolean completed;
}
