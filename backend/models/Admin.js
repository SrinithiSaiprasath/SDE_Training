const db = require('../config/db');

const Admin = {
  create: (name, email, password) => db.query('INSERT INTO admin (name, email, password) VALUES (?, ?, ?)', [name, email, password]),

  findById: (id) => db.query('SELECT * FROM admin WHERE id = ?', [id]),

  findAll: () => db.query('SELECT * FROM admin'),

  update: (id, name, email) => db.query('UPDATE admin SET name = ?, email = ? WHERE id = ?', [name, email, id]),

  delete: (id) => db.query('DELETE FROM admin WHERE id = ?', [id]),
};

module.exports = Admin;
