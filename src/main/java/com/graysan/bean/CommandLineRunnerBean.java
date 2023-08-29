package com.graysan.bean;

import com.graysan.business.services.ToDoService;
import com.graysan.data.entity.ToDoItem;
import com.graysan.data.repository.ToDoItemRepository;
import com.graysan.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Configuration
@Component
public class CommandLineRunnerBean {

    // Injection
    private final ToDoService toDoService;
    private final ToDoItemRepository toDoItemRepository;


    // Title (Save)
    public ToDoItem toDoItemEntitySave(String title) {
        ToDoItem todoEntity = new ToDoItem();
        todoEntity.setTitle(title);
        toDoItemRepository.save(todoEntity);
        return todoEntity;
    }

    // Random Todos
    public String[] randomTodo() {
        String[] randomData = new String[5];
        randomData[0] = "yemek ye " + UUID.randomUUID().toString();
        randomData[1] = "su ic "+ UUID.randomUUID().toString();
        randomData[2] = "pc yi ac "+ UUID.randomUUID().toString();
        randomData[3] = "arabayi yika "+ UUID.randomUUID().toString();
        randomData[4] = "markete git "+ UUID.randomUUID().toString();
        // döngüde rastgele bir tane todos seçecek
        for (int i = 0; i < 5; i++) {
            toDoItemEntitySave(randomData[i]);
        }
        // döngüde rastgele bir tane todos seçecek
        for (int i = 0; i < randomData.length; i++) {
            System.out.println(randomData[i]);
        }
        return randomData;
    }

    // Blog Create
    public void todoCreate(Integer todoNumber) {
        Iterable<ToDoItem> iterableTodoList = toDoItemRepository.findAll();
        List<ToDoItem> listTodoList = new ArrayList<>();
        // Iterable'dan Liste çevirdim
        iterableTodoList.forEach(listTodoList::add);

        // category varsa ekleme yapsın
        if (listTodoList != null) {
            ToDoItem todoEntity = new ToDoItem();
            todoEntity.setTitle("title data");
            todoEntity.setCompleted(true);
            todoEntity.(listCategoryEntityList.get(categoryNumber));
            toDoItemRepository.save(todoEntity);
        } else {
            throw new TodoNotFoundException("Category Listesi yoktur");
        }
    }

    @Bean
    public CommandLineRunner blogCommandLineRunnerMethod() {
        return args -> {
            System.out.println("CommandLineRunner Çalıştı");

            randomCategory();
            blogCreate(0);
        };
    }
}
