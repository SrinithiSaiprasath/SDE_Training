import React from 'react';

const TaskList = ({ tasks, onRefresh }) => {
  const handleDelete = async (taskId) => {
    try {
      await taskService.deleteTask(taskId);
      if (onRefresh) {
        onRefresh();
      }
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  return (
    <div>
      <h2>Task List</h2>
      {tasks.length === 0 ? (
        <p>No tasks available.</p>
      ) : (
        <ul>
          {tasks.map((task) => (
            <li key={task.id}>
              <h3>{task.title}</h3>
              <p>{task.description}</p>
              <p>Assigned To: {task.assignedTo}</p>
              {onRefresh && <button onClick={() => handleDelete(task.id)}>Delete</button>}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TaskList;
