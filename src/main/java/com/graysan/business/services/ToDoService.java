package com.graysan.business.services;

import com.graysan.business.dto.ToDoItemDto;
import com.graysan.data.entity.ToDoItem;
import com.graysan.data.repository.ToDoItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public List<ToDoItemDto> getAllTodos() {
        List<ToDoItem> todos = toDoItemRepository.findAll();
        return todos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ToDoItemDto getTodoById(Long id) {
        Optional<ToDoItem> optionalTodo = toDoItemRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return convertToDTO(optionalTodo.get());
        }
        return null; // veya isteğe bağlı hata işleme
    }

    @Transactional
    public ToDoItemDto createTodo(ToDoItemDto todoDTO) {
        ToDoItem todo = convertToEntity(todoDTO);
        ToDoItem savedTodo = toDoItemRepository.save(todo);
        return convertToDTO(savedTodo);
    }

    @Transactional
    public ToDoItemDto updateTodo(Long id, ToDoItemDto todoDTO) {
        Optional<ToDoItem> optionalTodo = toDoItemRepository.findById(id);
        if (optionalTodo.isPresent()) {
            ToDoItem todo = optionalTodo.get();
            BeanUtils.copyProperties(todoDTO, todo);
            return convertToDTO(todo);
        }
        return null; // veya isteğe bağlı hata işleme
    }

    @Transactional
    public void deleteTodo(Long id) {
        toDoItemRepository.deleteById(id);
    }

    public String categoryServiceAllDelete() {
        toDoItemRepository.deleteAll();
        return "Silinen veri sayısı : " + getAllTodos().size();
    }



    private ToDoItemDto convertToDTO(ToDoItem todo) {
        ToDoItemDto todoDTO = new ToDoItemDto();
        BeanUtils.copyProperties(todo, todoDTO);
        return todoDTO;
    }

    private ToDoItem convertToEntity(ToDoItemDto todoDTO) {
        ToDoItem todo = new ToDoItem();
        BeanUtils.copyProperties(todoDTO, todo);
        return todo;
    }
}
