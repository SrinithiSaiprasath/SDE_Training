

const express = require('express');
const http = require('http');
const dotenv = require('dotenv');
const db = require('./config/db');
dotenv.config();


const adminRoutes = require('./routes/adminRoutes');
const employeeRoutes = require('./routes/employeeRoutes');
const taskRoutes = require('./routes/taskRoutes');

const app = express();
const server = http.createServer(app);

app.use(express.json());

app.use('/api/admins', adminRoutes);
app.use('/api/employees', employeeRoutes);
app.use('/api/tasks', taskRoutes);

const PORT = process.env.PORT || 5000;
server.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
