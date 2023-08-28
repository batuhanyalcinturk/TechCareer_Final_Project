import axios from "axios";

const TODO_URL = "/v1/api/todos"

class ToDoApi {

    //@GetMapping("/list")
    getAllTodos() {
        return axios.get(`${TODO_URL}/list`);
    }

    //@GetMapping("/list/{id}")
    getTodoById(id) {
        return axios.get(`${TODO_URL}/list/${id}`);
    }

    //@PostMapping("/create")
    createTodo(todoDTO) {
        return axios.post(`${TODO_URL}/create`,todoDTO);
    }

    //@PutMapping("/update/{id}")
    updateTodo(id,todoDTO) {
        return axios.put(`${TODO_URL}/update/${id}`, todoDTO);
    }

    //@PutMapping("/delete/{id}")
    deleteTodo(id) {
        return axios.put(`${TODO_URL}/delete/${id}`)
    }
}