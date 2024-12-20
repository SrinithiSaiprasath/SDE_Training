
const db = require('../config/db');
//TASK CRU
exports.getTasksByEmployeeId = async (req, res) => {
  try {
    const {id} = req.body ; 
    const [tasks] = await db.query('SELECT * FROM tasks WHERE employee_id = ?', [id]);
    res.json(tasks);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.createTask = async (req, res) => {
  try {
    const { title, description, status, employee_id } = req.body;
    await db.query('INSERT INTO tasks (title, description, status, employee_id) VALUES (?, ?, ?, ?)', [title, description, status, employee_id]);
    res.status(201).json({ message: 'Task created successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};


exports.updateTaskProgress = async (req, res) => {
  try {
    const { progress } = req.body;
    await db.query('UPDATE tasks SET progress = ? WHERE id = ?', [progress, req.params.id]);
    res.json({ message: 'Task progress updated successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};
