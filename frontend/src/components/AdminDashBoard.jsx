import React, { useEffect, useState } from 'react';
import taskService from '../services/taskService';
import TaskWindow from './TaskWindow';
import TaskList from './TaskList';

const AdminDashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [refresh, setRefresh] = useState(false);

  // Fetch tasks on component mount or when refresh changes
  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await taskService.getTasks();
        setTasks(response.data);
      } catch (error) {
        console.error('Error fetching tasks:', error);
      }
    };

    fetchTasks();
  }, [refresh]);

  // Function to refresh the task list
  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <TaskWindow onTaskAdded={handleRefresh} />
      <TaskList tasks={tasks} onRefresh={handleRefresh} />
    </div>
  );
};

export default AdminDashboard;
