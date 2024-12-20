const db = require('../config/db');

const Employee = {
  create: (name, email, password, admin_id,date_of_joining) => db.query('INSERT INTO employees (name, email, password, admin_id,date_of_joining) VALUES (?, ?, ?, ?,?)', [name, email, password, admin_id ,date_of_joining]),

  findById: (id) => db.query('SELECT * FROM employees WHERE id = ?', [id]),

  findAll: () => db.query('SELECT * FROM employees'),

  update: (id, name, email) => db.query('UPDATE employees SET name = ?, email = ? WHERE id = ?', [name, email, id]),

  delete: (id) => db.query('DELETE FROM employees WHERE id = ?', [id]),
};

module.exports = Employee;
