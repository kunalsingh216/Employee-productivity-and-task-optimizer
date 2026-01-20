import React, { useState, useEffect } from 'react';
import { projectsAPI } from '../../services/api';
import './AdminStyles.css';

const ProjectManagement = () => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    image: null
  });
  const [imagePreview, setImagePreview] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      setLoading(true);
      const response = await projectsAPI.getAll();
      setProjects(response.data);
    } catch (error) {
      console.error('Error fetching projects:', error);
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
    formDataToSend.append('description', formData.description);
    if (formData.image) {
      formDataToSend.append('image', formData.image);
    }

    try {
      await projectsAPI.create(formDataToSend);
      setMessage({ type: 'success', text: 'Project added successfully!' });
      setFormData({ name: '', description: '', image: null });
      setImagePreview(null);
      document.getElementById('project-image-input').value = '';
      fetchProjects();
    } catch (error) {
      setMessage({
        type: 'error',
        text: error.response?.data?.error || 'Failed to add project'
      });
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this project?')) return;

    try {
      await projectsAPI.delete(id);
      setMessage({ type: 'success', text: 'Project deleted successfully!' });
      fetchProjects();
    } catch (error) {
      setMessage({
        type: 'error',
        text: error.response?.data?.error || 'Failed to delete project'
      });
    }
  };

  const getImageUrl = (imagePath) => {
    if (!imagePath) return 'https://via.placeholder.com/150/ff6b35/ffffff?text=Project';
    if (imagePath.startsWith('http')) return imagePath;
    return `http://localhost:5000${imagePath}`;
  };

  return (
    <div className="admin-tab-content">
      <div className="form-section">
        <h2>Add New Project</h2>
        {message.text && (
          <div className={`message ${message.type}`}>{message.text}</div>
        )}
        <form onSubmit={handleSubmit} className="admin-form">
          <div className="form-group full-width">
            <label>Project Image</label>
            <input
              type="file"
              id="project-image-input"
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
            <label>Project Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group full-width">
            <label>Project Description</label>
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
              {submitting ? 'Adding...' : 'Add Project'}
            </button>
          </div>
        </form>
      </div>

      <div className="form-section">
        <h2>All Projects</h2>
        {loading ? (
          <div className="loading">Loading projects...</div>
        ) : projects.length === 0 ? (
          <div className="empty-state">No projects yet. Add your first project above.</div>
        ) : (
          <div className="items-list">
            {projects.map((project) => (
              <div key={project._id} className="item-card">
                <img
                  src={getImageUrl(project.image)}
                  alt={project.name}
                  onError={(e) => {
                    e.target.src = 'https://via.placeholder.com/150/ff6b35/ffffff?text=Project';
                  }}
                />
                <div className="item-details">
                  <h3>{project.name}</h3>
                  <p>{project.description}</p>
                </div>
                <div className="item-actions">
                  <button
                    className="btn btn-danger"
                    onClick={() => handleDelete(project._id)}
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

export default ProjectManagement;
