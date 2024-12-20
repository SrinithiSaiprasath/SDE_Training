const db = require('../config/db');
//EMPLOYEE CRUD
exports.getAllEmployees = async (req, res) => {
  try {
    const [employees] = await db.query('SELECT * FROM employees');
    res.json(employees);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.getEmployeeById = async (req, res) => {
  try {
    const {id} = req.body;
    const [empdata] = await db.query('SELECT * FROM employees WHERE id = ?', [id]);
      return res.json(empdata);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.createEmployee = async (req, res) => {
  try {
    const { name, email, password, admin_id } = req.body;
    await db.query('INSERT INTO employees (name, email, password, admin_id) VALUES (?, ?, ?, ?)', [name, email, password, admin_id]);
    res.status(201).json({ message: 'Employee created successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.updateEmployee = async (req, res) => {
  try {
    const { email , newname } = req.body;
    await db.query('UPDATE employees SET name = ?, email = ? WHERE id = ?', [newname, email, req.params.id]);
    res.json({ message: 'Employee updated successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.deleteEmployee = async (req, res) => {
  try {
    const {id} = req.body ; 
    await db.query('DELETE FROM employees WHERE id = ?', [id]);
    res.json({ message: 'Employee deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const Employee = require('../models/Employee'); // Assuming you have an Employee model

// Employee Login Controller
const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    // Find the employee by email
    const employee = await Employee.findOne({ where: { email } });

    if (!employee) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    // Compare the provided password with the hashed password in the database
    const isMatch = await bcrypt.compare(password, employee.password);

    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    // Create JWT token
    const payload = { id: employee.id, role: 'employee' }; // You can include additional information in the payload
    const token = jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: '1h' });

    // Send response with the token and employee data
    res.json({ token, user: { id: employee.id, email: employee.email, role: 'employee' } });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Server error' });
  }
};

module.exports = { login };
