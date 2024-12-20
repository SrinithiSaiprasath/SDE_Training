const db = require("../config/db");
//ADMIN CRUD
exports.getAllAdmins = async (req, res) => {
  try {
    const [admins] = await db.query("SELECT * FROM admin");
    res.json(admins);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.getAdminById = async (req, res) => {
  
  try {
    const {id , email} = req.body; 
    await db.query("SELECT * FROM admin WHERE id = ? AND  email = ?", [id , email]);
    return res.status(404).json({ message: "Admin not found" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.createAdmin = async (req, res) => {
  try {
    const { name, email, password } = req.body;
    await db.query(
      "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)",
      [name, email, password]
    );
    res.status(201).json({ message: "Admin created successfully" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.updateAdmin = async (req, res) => {
  try {
    const { name, email,id } = req.body;
    await db.query("UPDATE admin SET name = ?, id = ? WHERE email = ?", [
      name,
      id,
      req.params.email,
    ]);
    res.json({ message: "Admin updated successfully" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

exports.deleteAdmin = async (req, res) => {
  try {
    const {id} = req.body ; 
    await db.query("DELETE FROM admin WHERE id = ?", [id]);
    res.json({ message: "Admin deleted successfully" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const Admin = require('../models/Admin'); // Assuming you have an Admin model

// Admin Login Controller
const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    const admin = await Admin.findOne({ where: { email } });

    if (!admin) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const isMatch = await bcrypt.compare(password, admin.password);

    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const payload = { id: admin.id, role: 'admin' }; // You can include additional information in the payload
    const token = jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: '1h' });

    res.json({ token, user: { id: admin.id, email: admin.email, role: 'admin' } });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Server error' });
  }
};

module.exports = { login };
