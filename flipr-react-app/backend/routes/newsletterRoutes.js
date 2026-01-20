const express = require('express');
const router = express.Router();
const {
  subscribeNewsletter,
  getAllSubscriptions
} = require('../controllers/newsletterController');

router.post('/', subscribeNewsletter);
router.get('/', getAllSubscriptions);

module.exports = router;
