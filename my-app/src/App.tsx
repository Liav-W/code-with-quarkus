import React, { useState, useEffect } from 'react';
import type { Task, Priority } from './types';
import './App.css';

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  // Form State
  const [title, setTitle] = useState<string>('');
  const [description, setDescription] = useState<string>('');
  const [priority, setPriority] = useState<Priority>('medium');
  const [dueDate, setDueDate] = useState<string>('');

  // 1. Fetch all tasks (GET /tasks)
  const fetchTasks = async () => {
    try {
      setLoading(true);
      const res = await fetch('/tasks');
      if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
      const data: Task[] = await res.json();
      setTasks(data);
      setError(null);
    } catch (err: any) {
      setError(err.message || 'Failed to load tasks');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  // 2. Create new task (POST /tasks)
  const handleCreateTask = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!title.trim()) return;

    const newTask: Task = {
      title,
      description: description || undefined,
      completed: false,
      priority,
      dueDate: dueDate ? new Date(dueDate).toISOString() : undefined,
    };

    try {
      const res = await fetch('/tasks', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newTask),
      });

      if (!res.ok) throw new Error('Failed to create task');

      setTitle('');
      setDescription('');
      setPriority('medium');
      setDueDate('');
      fetchTasks();
    } catch (err: any) {
      alert(err.message);
    }
  };

  // 3. Delete task (DELETE /tasks/{id})
  const handleDeleteTask = async (id?: number) => {
    if (!id) return;

    try {
      const res = await fetch(`/tasks/${id}`, {
        method: 'DELETE',
      });

      if (!res.ok) throw new Error('Failed to delete task');

      setTasks(tasks.filter((t) => t.id !== id));
    } catch (err: any) {
      alert(err.message);
    }
  };

  return (
    <div className="task-container">
      <h1>Task Manager</h1>

      <form onSubmit={handleCreateTask} className="task-form">
        <h3>Create New Task</h3>
        <input
          type="text"
          placeholder="Task title *"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <div className="form-row">
          <select value={priority} onChange={(e) => setPriority(e.target.value as Priority)}>
            <option value="low">Low Priority</option>
            <option value="medium">Medium Priority</option>
            <option value="high">High Priority</option>
          </select>
          <input
            type="datetime-local"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add Task
        </button>
      </form>

      <hr />

      <h2>Tasks List</h2>
      {loading && <p>Loading tasks...</p>}
      {error && <p className="error-message">Error: {error}</p>}

      {!loading && tasks.length === 0 && <p>No tasks found.</p>}

      {!loading && tasks.length > 0 && (
        <table className="task-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Priority</th>
              <th>Due Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>
                  <strong>{task.title}</strong>
                  {task.description && <div className="task-desc">{task.description}</div>}
                </td>
                <td>
                  <span className={`priority-badge ${task.priority}`}>
                    {task.priority}
                  </span>
                </td>
                <td>
                  {task.dueDate ? new Date(task.dueDate).toLocaleString() : '-'}
                </td>
                <td>
                  <button
                    onClick={() => handleDeleteTask(task.id)}
                    className="btn btn-danger"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}