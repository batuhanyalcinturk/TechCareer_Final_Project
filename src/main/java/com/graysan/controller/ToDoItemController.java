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

    // Constructor Injection
    public ToDoItemController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    /**
     * Gets a list of all ToDo items.
     * Tüm ToDo öğelerinin listesini alır.
     */
    @GetMapping("/list")
    public ResponseEntity<List<ToDoItemDto>> getAllTodos() {
        List<ToDoItemDto> todos = toDoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * Gets a ToDo item by its ID.
     * ToDo öğesini ID'ye göre alır.
     * return ResponseEntity containing the ToDoItemDto object or HttpStatus.NOT_FOUND if not found.
     * ToDoItemDto nesnesini veya bulunamazsa HttpStatus.NOT_FOUND içeren ResponseEntity döner.
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<ToDoItemDto> getTodoById(@PathVariable Long id) {
        ToDoItemDto todo = toDoService.getTodoById(id);
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets a list of completed ToDo items.
     * Tamamlanmış ToDo öğelerinin listesini alır.
     */
    @GetMapping("/list/completed")
    public ResponseEntity<List<ToDoItem>> getCompletedTodos() {
        List<ToDoItem> todos = toDoService.getCompletedTodos(true);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * Creates a new ToDo item.
     * Yeni bir ToDo öğesi oluşturur.
     *
     * return ResponseEntity containing the created ToDoItemDto object.
     * Oluşturulan ToDoItemDto nesnesini içeren ResponseEntity döner.
     */
    @PostMapping("/create")
    public ResponseEntity<ToDoItemDto> createTodo(@Valid @RequestBody ToDoItemDto todoDTO) {
        return ResponseEntity.ok(toDoService.createTodo(todoDTO));
    }

    /**
     * Updates a ToDo item by its ID.
     * ToDo öğesinin ID'sine göre günceller.
     *
     * return ResponseEntity containing the updated ToDoItemDto object or HttpStatus.NOT_FOUND if not found
     * Güncellenen ToDoItemDto nesnesini veya bulunamazsa HttpStatus.NOT_FOUND içeren ResponseEntity döner.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoItemDto> updateTodo(@PathVariable Long id, @RequestBody ToDoItemDto todoDTO) {
        ToDoItemDto updatedTodo = toDoService.updateTodo(id, todoDTO);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a ToDo item by its ID.
     * ToDo öğesini ID'sine göre siler.
     *
     * return ResponseEntity with HttpStatus.NO_CONTENT.
     * HttpStatus.NO_CONTENT içeren ResponseEntity döner.
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes all ToDo items.
     * Tüm Todo öğelerini siler.
     *
     * return ResponseEntity containing a message with the number of deleted records.
     * Silinen kayıt sayısı içeren ResponseEntity nesnesini döner.
     */
    @GetMapping(value = "/all/delete")
    public ResponseEntity<String> todoApiAllDelete() {
        return ResponseEntity.ok(toDoService.toDoServiceAllDelete());
    }

    /**
     * Deletes all completed ToDo items.
     * Tüm tamamlanmış ToDo öğelerini siler.
     *
     * return A redirection URL ("/todo").
     * "/todo" yönlendirme URL'sini döndürür.
     */
    @DeleteMapping("/completed")
    public String deleteCompleted() {
        List<ToDoItem> completedItems = toDoService.getCompletedTodos(true);
        for (ToDoItem item : completedItems) {
            toDoService.deleteTodo(item.getId());
        }
        return "redirect:/todo";
    }
}
