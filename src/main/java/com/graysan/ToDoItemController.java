package com.graysan;

import com.graysan.assist.FrontEnd;
import com.graysan.business.dto.ToDoItemDto;
import com.graysan.business.services.ToDoService;
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

    @GetMapping
    public ResponseEntity<List<ToDoItemDto>> getAllTodos() {
        List<ToDoItemDto> todos = toDoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItemDto> getTodoById(@PathVariable Long id) {
        ToDoItemDto todo = toDoService.getTodoById(id);
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ToDoItemDto> createTodo(@RequestBody ToDoItemDto todoDTO) {
        ToDoItemDto createdTodo = toDoService.createTodo(todoDTO);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItemDto> updateTodo(@PathVariable Long id, @RequestBody ToDoItemDto todoDTO) {
        ToDoItemDto updatedTodo = toDoService.updateTodo(id, todoDTO);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
