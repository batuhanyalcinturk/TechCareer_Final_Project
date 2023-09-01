import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

function TodoUpdate() {
  const { id } = useParams();
  const [title, setTitle] = useState('');
  const [completed, setCompleted] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // Burada, verilen "id" ile mevcut todo öğesini getirin ve başlangıç değerlerini ayarlayın
    axios
      .get(`http://localhost:8080/v1/api/todos/list/${id}`)
      .then((response) => {
        const todo = response.data;
        setTitle(todo.title);
        setCompleted(todo.completed);
        
      })
      .catch((error) => {
        console.error('Error fetching todo:', error);
      });
  }, [id]);

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const handleCompletedChange = (checked) => {
    setCompleted(checked);
  };

  const handleSubmit = () => {
    // Güncellenmiş ToDoItemDto nesnesini oluşturun
    const updatedTodo = {
      title: title,
      completed: completed,
    };

    // API'ye güncelleme isteği gönderin
    axios
      .put(`http://localhost:8080/v1/api/todos/update/${id}`, updatedTodo)
      .then((response) => {
        // Başarıyı işleyin, yönlendirme veya liste güncelleme gibi işlemleri gerçekleştirin
        console.log('Todo updated successfully', response.data);
        // İhtiyaca göre yönlendirme veya liste güncelleme yapın
        navigate("/todos/list")
      })
      .catch((error) => {
        console.error('Error updating todo:', error);
      });
  };

  return (
    <div>
      <h1>Update Todo</h1>
      <div>
        <label>Title:</label>
        <input
          type="text"
          className="form-control"
          placeholder="Enter a to do"
          value={title}
          onChange={handleTitleChange}
        />
      </div>
      <div>
        <label>Completed:</label>
        <input
          type="checkbox"
          checked={completed}
          onChange={() => handleCompletedChange(!completed)}
        />
      </div>
      <button
        onClick={handleSubmit}
        className="btn btn-primary"
        >Update Todo</button>
    </div>
  );
}

export default TodoUpdate;
