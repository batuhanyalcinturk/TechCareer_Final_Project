import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

// FUNCTION
export default function TodoList() {

  // REDIRECT
  let navigate = useNavigate();

  // STATE
  const [TodoStateApi, setTodoStateApi] = useState([]);

  // USEEFFECT
  useEffect(() => {
    axios.get("http://localhost:8080/v1/api/todos/list")
      .then((response) => {
        console.log(response.data);
        setTodoStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  }, []);


  // LIST
  const getListTodo = (() => {
    axios.get("http://localhost:8080/v1/api/todos/list")
      .then((response) => {
        console.log(response.data);
        setTodoStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  });

  // DELETE
  const setDeleteTodo = ((id) => {
    if (window.confirm("Silmek istediğinizden emin misiniz ?")) {
      axios.delete("http://localhost:8080/v1/api/todos/delete/" + id)
        .then(() => {
            getListTodo();
        })
    } else {
      alert("Silinmedi.")
    }
    navigate("/todos/list");
  });

  //UPDATE
  const setUpdateTodo = (data) => {
    let { id, title, completed } = data;
    localStorage.setItem("todo_update_id", id);
    localStorage.setItem("todo_update_title", title);
    localStorage.setItem("todo_update_completed", completed);
  }

  //VIEW
  const setViewTodo = (id) => {
    localStorage.setItem("todo_view_id", id);
  }

  //RETURN
  return (
    <React.Fragment>
      <h1 className="text-center display-3">Todo List</h1>
      <Link to="/todos/create" className="btn btn-primary">Todo Ekle</Link>
      <table className="table table-striped table-hover table-responsive">
        <thead>
          <tr>
            <th>ID</th>
            <th>TITLE</th>
            <th>COMPLETED</th>
            <th>UPDATE</th>
            <th>VIEW</th>
            <th>DELETE</th>
          </tr>
        </thead>
        <tbody>
          {
            TodoStateApi.map((data) =>
              <tr key={data.id}>
                <td>{data.id}</td>
                <td>{data.title}</td>
                <td>{data.completed}</td>

                <td>
                  <Link to="/todos/update">
                  <i onClick={()=>setUpdateTodo(data)} class="fa-solid fa-pen-to-square text-primary"></i>
                  </Link>
                  </td>

                <td>
                <Link to="/todos/view">
                  <i onClick={()=>setViewTodo(data.id)} class="fa-solid fa-expand text-warning"></i>
                  </Link>
                  </td>

                <td>
                <Link to="/todos/delete">
                  <i onClick={()=>setDeleteTodo(data.id)} class="fa-solid fa-trash text-danger"></i>
                  </Link>
                  </td>
              </tr>
            )
          }
        </tbody>
      </table>
    </React.Fragment>
  )
}