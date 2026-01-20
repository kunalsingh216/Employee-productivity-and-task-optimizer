const Client = require('../models/Client');
const { processImage } = require('../middleware/imageProcessor');
const path = require('path');
const fs = require('fs');

// Get all clients
exports.getAllClients = async (req, res) => {
  try {
    const clients = await Client.find().sort({ createdAt: -1 });
    res.json(clients);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Create client
exports.createClient = async (req, res) => {
  try {
    const { name, designation, description } = req.body;

    if (!name || !designation || !description) {
      return res.status(400).json({ error: 'Name, designation, and description are required' });
    }

    let imagePath = null;

    if (req.file) {
      const filename = `client-${Date.now()}.jpg`;
      imagePath = await processImage(req.file.buffer, filename);
    }

    const client = new Client({
      name,
      designation,
      description,
      image: imagePath
    });

    await client.save();
    res.status(201).json(client);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Delete client
exports.deleteClient = async (req, res) => {
  try {
    const client = await Client.findById(req.params.id);
    
    if (!client) {
      return res.status(404).json({ error: 'Client not found' });
    }

    // Delete image file if exists
    if (client.image) {
      const imagePath = path.join(__dirname, '..', client.image);
      if (fs.existsSync(imagePath)) {
        fs.unlinkSync(imagePath);
      }
    }

    await Client.findByIdAndDelete(req.params.id);
    res.json({ message: 'Client deleted successfully' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
