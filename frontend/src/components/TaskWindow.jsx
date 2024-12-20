import React, { useState } from 'react';
import taskService from '../services/taskService';

const TaskWindow = ({ onTaskAdded }) => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [assignedTo, setAssignedTo] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newTask = {
      title,
      description,
      assignedTo,
    };

    try {
      await taskService.createTask(newTask);
      onTaskAdded();
      setTitle('');
      setDescription('');
      setAssignedTo('');
    } catch (error) {
      console.error('Error creating task:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create New Task</h2>
      <div>
        <label>Title:</label>
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
      </div>
      <div>
        <label>Description:</label>
        <textarea value={description} onChange={(e) => setDescription(e.target.value)} required />
      </div>
      <div>
        <label>Assign To (Employee ID):</label>
        <input type="text" value={assignedTo} onChange={(e) => setAssignedTo(e.target.value)} required />
      </div>
      <button type="submit">Create Task</button>
    </form>
  );
};

export default TaskWindow;
