import React, { useState, useEffect } from 'react';
import { clientsAPI } from '../../services/api';
import './AdminStyles.css';

const ClientManagement = () => {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({
    name: '',
    designation: '',
    description: '',
    image: null
  });
  const [imagePreview, setImagePreview] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  useEffect(() => {
    fetchClients();
  }, []);

  const fetchClients = async () => {
    try {
      setLoading(true);
      const response = await clientsAPI.getAll();
      setClients(response.data);
    } catch (error) {
      console.error('Error fetching clients:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setFormData({ ...formData, image: file });
      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setMessage({ type: '', text: '' });

    const formDataToSend = new FormData();
    formDataToSend.append('name', formData.name);
    formDataToSend.append('designation', formData.designation);
    formDataToSend.append('description', formData.description);
    if (formData.image) {
      formDataToSend.append('image', formData.image);
    }

    try {
      await clientsAPI.create(formDataToSend);
      setMessage({ type: 'success', text: 'Client added successfully!' });
      setFormData({ name: '', designation: '', description: '', image: null });
      setImagePreview(null);
      document.getElementById('client-image-input').value = '';
      fetchClients();
    } catch (error) {
      setMessage({
        type: 'error',
        text: error.response?.data?.error || 'Failed to add client'
      });
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this client?')) return;

    try {
      await clientsAPI.delete(id);
      setMessage({ type: 'success', text: 'Client deleted successfully!' });
      fetchClients();
    } catch (error) {
      setMessage({
        type: 'error',
        text: error.response?.data?.error || 'Failed to delete client'
      });
    }
  };

  const getImageUrl = (imagePath) => {
    if (!imagePath) return 'https://via.placeholder.com/150/ff6b35/ffffff?text=Client';
    if (imagePath.startsWith('http')) return imagePath;
    return `http://localhost:5000${imagePath}`;
  };

  return (
    <div className="admin-tab-content">
      <div className="form-section">
        <h2>Add New Client</h2>
        {message.text && (
          <div className={`message ${message.type}`}>{message.text}</div>
        )}
        <form onSubmit={handleSubmit} className="admin-form">
          <div className="form-group full-width">
            <label>Client Image</label>
            <input
              type="file"
              id="client-image-input"
              accept="image/*"
              onChange={handleImageChange}
              required
            />
            {imagePreview && (
              <div className="image-preview">
                <img src={imagePreview} alt="Preview" />
                <p className="preview-note">Image will be cropped to 450x350</p>
              </div>
            )}
          </div>
          <div className="form-group">
            <label>Client Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Client Designation</label>
            <input
              type="text"
              name="designation"
              value={formData.designation}
              onChange={handleInputChange}
              placeholder="e.g., CEO, Web Developer, Designer"
              required
            />
          </div>
          <div className="form-group full-width">
            <label>Client Description</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleInputChange}
              required
              rows="4"
            />
          </div>
          <div className="form-group full-width">
            <button type="submit" className="btn btn-primary" disabled={submitting}>
              {submitting ? 'Adding...' : 'Add Client'}
            </button>
          </div>
        </form>
      </div>

      <div className="form-section">
        <h2>All Clients</h2>
        {loading ? (
          <div className="loading">Loading clients...</div>
        ) : clients.length === 0 ? (
          <div className="empty-state">No clients yet. Add your first client above.</div>
        ) : (
          <div className="items-list">
            {clients.map((client) => (
              <div key={client._id} className="item-card">
                <img
                  src={getImageUrl(client.image)}
                  alt={client.name}
                  onError={(e) => {
                    e.target.src = 'https://via.placeholder.com/150/ff6b35/ffffff?text=Client';
                  }}
                />
                <div className="item-details">
                  <h3>{client.name}</h3>
                  <p><strong>{client.designation}</strong></p>
                  <p>{client.description}</p>
                </div>
                <div className="item-actions">
                  <button
                    className="btn btn-danger"
                    onClick={() => handleDelete(client._id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ClientManagement;
