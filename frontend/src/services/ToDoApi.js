import axios from "axios";

const TODO_URL = "/v1/api/todos"

class ToDoApi {

    //@GetMapping("/list")
    getAllTodos() {
        return axios.get(`${TODO_URL}/list`);
    }

    //@GetMapping("/list/{id}")
    getTodoById(id) {
        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@PostMapping("/create")
    createTodo(todoDTO) {
        return axios.post(`${TODO_URL}/create`,todoDTO);
    }

    //@PutMapping("/update/{id}")
    updateTodo(id,todoDTO) {
        return axios.put(`${TODO_URL}/update/${id}`, todoDTO);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/all/delete")
    public ResponseEntity<String> categoryApiAllDelete() {
        return ResponseEntity.ok(toDoService.categoryServiceAllDelete());
    }
}