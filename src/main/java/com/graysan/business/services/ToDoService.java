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

    // Constructor Injection
    public ToDoService(ToDoItemRepository toDoItemRepository, ModelMapperBean modelMapperBean) {
        this.toDoItemRepository = toDoItemRepository;
        this.modelMapperBean = modelMapperBean;
    }

    // ToDoItem nesnesini ToDoItemDto nesnesine dönüştürür.
    // Converts a ToDoItem object to a ToDoItemDto object.
    private ToDoItemDto convertToDTO(ToDoItem todo) {
        return modelMapperBean.modelMapperMethod().map(todo,ToDoItemDto.class);
    }

    // ToDoItemDto nesnesini ToDoItem nesnesine dönüştürür.
    // Converts a ToDoItemDto object to a ToDoItem object.
    private ToDoItem convertToEntity(ToDoItemDto todoDTO) {
        return modelMapperBean.modelMapperMethod().map(todoDTO,ToDoItem.class);
    }

    // Tüm Todo öğelerini getirir.
    // Retrieves all ToDo items.
    public List<ToDoItemDto> getAllTodos() {
        Iterable<ToDoItem> entityIterable=  toDoItemRepository.findAll();
        List<ToDoItemDto> todoDtoList=new ArrayList<>();
        for (ToDoItem entity:  entityIterable) {
            ToDoItemDto toDoItemDto=convertToDTO(entity);
            todoDtoList.add(toDoItemDto);
        }
        return todoDtoList;
    }

    // Belirli bir ID'ye sahip Todo öğesini getirir.
    // Retrieves a ToDo item with a specific ID.
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

    // Yeni bir ToDo öğesi oluşturur ve kaydeder.
    // Creates a new ToDo item and saves it.
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

    // Belirli bir ID'ye sahip ToDo öğesini günceller.
    // Updates a ToDo item with a specific ID.
    @Transactional
    public ToDoItemDto updateTodo(Long id, ToDoItemDto todoDTO) {
        ToDoItemDto todoFindDto= getTodoById(id);
        if(todoFindDto!=null){
            ToDoItem todoEntity=convertToEntity(todoFindDto);
            todoEntity.setTitle(todoDTO.getTitle());
            todoEntity.setCompleted(todoDTO.isCompleted());
            toDoItemRepository.save(todoEntity);
        }
        return todoDTO;
    }

    // Belirli bir ID'ye sahip ToDo öğesini siler.
    // Deletes a ToDo item with a specific ID.
    @Transactional
    public ToDoItemDto deleteTodo(Long id) {
        ToDoItemDto todoFindDto= getTodoById(id);
        if(todoFindDto!=null){
            toDoItemRepository.deleteById(id);
        }
        return todoFindDto;
    }

    // Tüm ToDo öğelerini siler
    // Deletes all ToDo items.
    public String toDoServiceAllDelete() {
        toDoItemRepository.deleteAll();
        return "Silinen veri sayısı : " + getAllTodos().size();
    }

    // Tamamlanmış ToDo öğelerini getirir.
    // Retrieves completed ToDo items.
    public List<ToDoItem> getCompletedTodos(boolean completed){
        return toDoItemRepository.findAllByCompleted(completed);
    }
}
