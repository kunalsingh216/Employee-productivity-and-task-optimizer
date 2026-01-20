const Newsletter = require('../models/Newsletter');

// Subscribe to newsletter
exports.subscribeNewsletter = async (req, res) => {
  try {
    const { email } = req.body;

    if (!email) {
      return res.status(400).json({ error: 'Email is required' });
    }

    // Basic email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      return res.status(400).json({ error: 'Invalid email format' });
    }

    // Check if email already exists
    const existing = await Newsletter.findOne({ email: email.toLowerCase() });
    if (existing) {
      return res.status(409).json({ error: 'Email already subscribed' });
    }

    const newsletter = new Newsletter({
      email: email.toLowerCase()
    });

    await newsletter.save();
    res.status(201).json({ message: 'Successfully subscribed to newsletter', newsletter });
  } catch (error) {
    if (error.code === 11000) {
      return res.status(409).json({ error: 'Email already subscribed' });
    }
    res.status(500).json({ error: error.message });
  }
};

// Get all newsletter subscriptions
exports.getAllSubscriptions = async (req, res) => {
  try {
    const subscriptions = await Newsletter.find().sort({ createdAt: -1 });
    res.json(subscriptions);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
