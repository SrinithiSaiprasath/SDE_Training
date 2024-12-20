const express = require('express');
const router = express.Router();
const taskController = require('../controllers/taskController');
const { authenticateToken } = require('../middlewares/authMiddleware');

router.get('/employeeTask', taskController.getTasksByEmployeeId);
router.post('/', taskController.createTask);
router.put('/EmployeepProgress',  taskController.updateTaskProgress);

module.exports = router;