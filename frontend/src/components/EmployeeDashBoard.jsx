import React, { useEffect, useState } from 'react';
import taskService from '../services/taskService';
import TaskList from './TaskList';

const EmployeeDashboard = () => {
  const [tasks, setTasks] = useState([]);

  // Fetch tasks assigned to the employee
  useEffect(() => {
    const fetchEmployeeTasks = async () => {
      try {
        const response = await taskService.getEmployeeTasks();
        setTasks(response.data);
      } catch (error) {
        console.error('Error fetching employee tasks:', error);
      }
    };

    fetchEmployeeTasks();
  }, []);

  return (
    <div>
      <h1>Employee Dashboard</h1>
      <TaskList tasks={tasks} />
    </div>
  );
};

export default EmployeeDashboard;
