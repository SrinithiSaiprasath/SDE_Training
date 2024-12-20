
const express = require('express');
const router = express.Router();
const employeeController = require('../controllers/employeeController');
const { authenticateToken } = require('../middlewares/authMiddleware');
const { login } = require('../controllers/employeeController');

// Employee login route
router.post('/login', login);
router.get('/getAllEmployee', employeeController.getAllEmployees);
router.get('/getEmployeeById', employeeController.getEmployeeById);
router.post('/createEmployee',  employeeController.createEmployee);
router.put('/UpdateEmployee', employeeController.updateEmployee);
router.delete('/deleteEmployee', employeeController.deleteEmployee);

module.exports = router;
