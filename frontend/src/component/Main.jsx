import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { withTranslation } from 'react-i18next'
import { useNavigate, Link } from 'react-router-dom'

function Main() {
  const navigate = useNavigate();
  const [TodoStateApi, setTodoStateApi] = useState([]);
  const [title, setTitle] = useState('');

  useEffect(() => {
    axios.get("http://localhost:8080/v1/api/todos/list")
      .then((response) => {
        console.log(response.data);
        setTodoStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  }, []);

  const createTodo = () => {
    const todoItem = {
      title: title
    };

    axios
      .post('http://localhost:8080/v1/api/todos/create', todoItem)
      .then((response) => {
        console.log('Todo created successfully', response.data);
        navigate("/todos/list");
      })
      .catch((error) => {
        console.error('Error creating todo:', error);
      });
  };

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  // LIST
  const getListTodo = (() => {
    axios.get("http://localhost:8080/v1/api/todos/list")
      .then((response) => {
        console.log(response.data);
        setTodoStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  });

  const getCompletedListTodo = (() => {
    axios.get("http://localhost:8080/v1/api/todos/list/completed")
      .then((response) => {
        console.log(response.data);
        setTodoStateApi(response.data);
      })
      .catch((err) => { console.error(err); });
  });

  // DELETE
  const setDeleteTodo = (id) => {
    if (window.confirm("Silmek istediğinizden emin misiniz ?")) {
      axios.put(`http://localhost:8080/v1/api/todos/delete/${id}`)
        .then(() => {
          getListTodo();
          navigate("/todos/list");
        })
        .catch((error) => {
          console.error("Error deleting todo:", error);
        });
    } else {
      alert("Silinmedi.");
    }
  };

  const setDeleteCompletedTodo = (id) => {
    if (window.confirm("Silmek istediğinizden emin misiniz ?")) {
      axios.delete(`http://localhost:8080/v1/api/todos/completed`)
        .then(() => {
          getListTodo();
          navigate("/todos/list");
        })
        .catch((error) => {
          console.error("Error deleting todo:", error);
        });
    } else {
      alert("Silinmedi.");
    }
  };

  //UPDATE
  const setUpdateTodo = (data) => {
    let { id, title, completed } = data;
    localStorage.setItem("todo_update_id", id);
    localStorage.setItem("todo_update_title", title);
    localStorage.setItem("todo_update_completed", completed);
  }

  // ... diğer kodlar

  return (
    <React.Fragment>
      <h1 className="text-center display-3">Todo List</h1>
      <div>
        <h2 className="display-3 mt-4">Create Todo</h2>
        <div className="form-group">
          <label>Title:</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter a to do"
            required={true}
            value={title}
            onChange={handleTitleChange}
          />
        </div>
        <button
          onClick={createTodo}
          className="btn btn-primary"
        >
          Create Todo
        </button>
      </div>
      
      <table className="table table-striped table-hover table-responsive">
        <thead>
          <tr>
            <th>ID</th>
            <th>TITLE</th>
            <th>UPDATE</th>
            <th>DELETE</th>
          </tr>
        </thead>
        <tbody>
          {
            TodoStateApi.map((data) =>
              <tr key={data.id}>
                <td>{data.id}</td>
                <td style={data.completed ? { textDecoration: 'line-through' } : {}}>{data.title}</td>
                <td>
                  <Link to={`/todos/update/${data.id}`}>
                    <i onClick={() => setUpdateTodo(data)} className="fa-solid fa-pen-to-square text-primary"></i>
                  </Link>
                </td>
                <td>
                  <Link to={`/todos/delete/${data.id}`}>
                    <i onClick={() => setDeleteTodo(data.id)} className="fa-solid fa-trash text-danger"></i>
                  </Link>
                </td>
              </tr>
            )
          }
        </tbody>
      </table>
      <Link to={`/todos/completed`}>
        <i onClick={() => setDeleteCompletedTodo()} className="btn btn-primary">Delete Done Todos</i>
      </Link>
      
    </React.Fragment>
  );
}

export default withTranslation()(Main);
