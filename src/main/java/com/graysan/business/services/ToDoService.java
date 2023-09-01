package com.graysan.business.services;

import com.graysan.bean.ModelMapperBean;
import com.graysan.business.dto.ToDoItemDto;
import com.graysan.data.entity.ToDoItem;
import com.graysan.data.repository.ToDoItemRepository;
import com.graysan.exception.GraysanException;
import com.graysan.exception.TodoNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private final ToDoItemRepository toDoItemRepository;
    private final ModelMapperBean modelMapperBean;

    public ToDoService(ToDoItemRepository toDoItemRepository, ModelMapperBean modelMapperBean) {
        this.toDoItemRepository = toDoItemRepository;
        this.modelMapperBean = modelMapperBean;
    }


    private ToDoItemDto convertToDTO(ToDoItem todo) {
        return modelMapperBean.modelMapperMethod().map(todo,ToDoItemDto.class);
    }

    private ToDoItem convertToEntity(ToDoItemDto todoDTO) {
        return modelMapperBean.modelMapperMethod().map(todoDTO,ToDoItem.class);
    }

    public List<ToDoItemDto> getAllTodos() {
        Iterable<ToDoItem> entityIterable=  toDoItemRepository.findAll();
        // Dto To entityb List
        List<ToDoItemDto> todoDtoList=new ArrayList<>();
        for (ToDoItem entity:  entityIterable) {
            ToDoItemDto toDoItemDto=convertToDTO(entity);
            todoDtoList.add(toDoItemDto);
        }
        return todoDtoList;
    }

    public ToDoItemDto getTodoById(Long id) {
        ToDoItem findTodoItem =  null;
        if(id!=null){
            findTodoItem=  toDoItemRepository.findById(id)
                    .orElseThrow(()->new TodoNotFoundException(id+" nolu id yoktur"));
        }else if(id==null) {
            throw new GraysanException("İd null olarak geldi");
        }
        return convertToDTO(findTodoItem);
    }

    @Transactional
    public ToDoItemDto createTodo(ToDoItemDto todoDTO) {
        if(todoDTO!=null){
            ToDoItem todo=convertToEntity(todoDTO);
            toDoItemRepository.save(todo);
            todoDTO.setId(todo.getId());
        }else{
            throw  new NullPointerException( " ToDoItemDto null veri");
        }
        return todoDTO;
    }

    @Transactional
    public ToDoItemDto updateTodo(Long id, ToDoItemDto todoDTO) {
        // Önce Bul
        ToDoItemDto todoFindDto= getTodoById(id);
        if(todoFindDto!=null){
            ToDoItem todoEntity=convertToEntity(todoFindDto);
            todoEntity.setTitle(todoDTO.getTitle());
            todoEntity.setCompleted(todoDTO.isCompleted());
            toDoItemRepository.save(todoEntity);
            // Dönüştede ID ve Date Set et
        }
        return todoDTO;
    }

    @Transactional
    public ToDoItemDto deleteTodo(Long id) {
        // Önce Bul
        ToDoItemDto todoFindDto= getTodoById(id);
        if(todoFindDto!=null){
            toDoItemRepository.deleteById(id);
            // Dönüştede ID ve Date Set et
        }
        return todoFindDto;
    }

    public String toDoServiceAllDelete() {
        toDoItemRepository.deleteAll();
        return "Silinen veri sayısı : " + getAllTodos().size();
    }

    public List<ToDoItem> getCompletedTodos(boolean completed){
        return toDoItemRepository.findAllByCompleted(completed);
    }




}
