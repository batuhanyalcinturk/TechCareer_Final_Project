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
        randomData[0] = "Yemek ye ";
        randomData[1] = "Su iç ";
        randomData[2] = "Ödevlerini yap ";
        randomData[3] = "Arabayı yıka ";
        randomData[4] = "Marketten alınacakları al ";
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

    // Todo Create
    public void todoCreate(Integer todoNumber) {
        Iterable<ToDoItem> iterableTodoList = toDoItemRepository.findAll();
        List<ToDoItem> listTodoList = new ArrayList<>();
        // Iterable'dan Liste çevirdim
        iterableTodoList.forEach(listTodoList::add);

        // todo varsa ekleme yapsın
        if (listTodoList != null) {
            ToDoItem todoEntity = new ToDoItem();
            todoEntity.setTitle("title data");
            todoEntity.setCompleted(true);
            toDoItemRepository.save(todoEntity);
        } else {
            throw new TodoNotFoundException("Todo Listesi yoktur");
        }
    }

    @Bean
    public CommandLineRunner todoCommandLineRunnerMethod() {
        return args -> {
            System.out.println("CommandLineRunner Çalıştı");

            randomTodo();
            todoCreate(0);
        };
    }
}
