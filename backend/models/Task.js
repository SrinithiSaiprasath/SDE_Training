
const db = require('../config/db');

const Task = {
  create: (title, description, status, employee_id) => db.query('INSERT INTO tasks (title, description, status, employee_id) VALUES (?, ?, ?, ?)', [title, description, status, employee_id]),

  findByEmployeeId: (employee_id) => db.query('SELECT * FROM tasks WHERE employee_id = ?', [employee_id]),

  updateProgress: (id, progress) => db.query('UPDATE tasks SET progress = ? WHERE id = ?', [progress, id]),
};

module.exports = Task;
