
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
const Admin = require('../models/Admin');
const Employee = require('../models/Employee');
require('dotenv').config();
//AUTH

exports.login = async (req, res) => {
  const { email, password, role } = req.body;

  try {
    let user;
    
    if (role === 'admin') {
      [user] = await Admin.findByEmail(email);
    } else if (role === 'employee') {
      [user] = await Employee.findByEmail(email);
    } else {
      return res.status(400).json({ message: 'Invalid role. Must be "admin" or "employee".' });
    }

    if (!user || user.length === 0) {
      return res.status(401).json({ message: 'Invalid email or password.' });
    }

    const isMatch = await bcrypt.compare(password, user[0].password);
    if (!isMatch) {
      return res.status(401).json({ message: 'Invalid email or password.' });
    }

    const payload = {
      id: user[0].id,
      email: user[0].email,
      role,
    };

    const token = jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: '1h' });

    res.json({
      message: 'Login successful',
      token,
    });
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: 'Server error', error: error.message });
  }
};
