package com.graysan.controller;

import com.graysan.assist.FrontEnd;
import com.graysan.business.dto.ToDoItemDto;
import com.graysan.business.services.ToDoService;
import com.graysan.data.entity.ToDoItem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/todos")
@CrossOrigin(origins = FrontEnd.REACT_URL)
public class ToDoItemController {

    private final ToDoService toDoService;


    public ToDoItemController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ToDoItemDto>> getAllTodos() {
        List<ToDoItemDto> todos = toDoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ToDoItemDto> getTodoById(@PathVariable Long id) {
        ToDoItemDto todo = toDoService.getTodoById(id);
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/completed")
    public ResponseEntity<List<ToDoItem>> getCompletedTodos() {
        List<ToDoItem> todos = toDoService.getCompletedTodos(true);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoItemDto> createTodo(@Valid @RequestBody ToDoItemDto todoDTO) {
        return ResponseEntity.ok(toDoService.createTodo(todoDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoItemDto> updateTodo(@PathVariable Long id, @RequestBody ToDoItemDto todoDTO) {
        ToDoItemDto updatedTodo = toDoService.updateTodo(id, todoDTO);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/all/delete")
    public ResponseEntity<String> todoApiAllDelete() {
        return ResponseEntity.ok(toDoService.toDoServiceAllDelete());
    }

    @DeleteMapping("/completed")
    public String deleteCompleted() {
        List<ToDoItem> completedItems = toDoService.getCompletedTodos(true);
        for (ToDoItem item : completedItems) {
            toDoService.deleteTodo(item.getId());
        }
        return "redirect:/todo";
    }
}
