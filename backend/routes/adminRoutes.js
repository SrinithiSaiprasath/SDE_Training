
const express = require('express');
const router = express.Router();
const adminController = require('../controllers/adminController');
const { authenticateToken } = require('../middlewares/authMiddleware');
const { login } = require('../controllers/adminController');

// Admin login route
router.post('/login', login);
router.get('/getAllAdmin',adminController.getAllAdmins);
router.get('/getAdminById', adminController.getAdminById);
router.post('/createAdmin', adminController.createAdmin);
router.put('/:id', adminController.updateAdmin);
router.delete('/deleteAdmin', adminController.deleteAdmin);

module.exports = router;
