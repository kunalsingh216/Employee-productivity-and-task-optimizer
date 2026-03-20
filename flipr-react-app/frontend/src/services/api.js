import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

// Projects API
export const projectsAPI = {
  getAll: () => api.get('/projects'),
  create: (formData) => api.post('/projects', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  }),
  delete: (id) => api.delete(`/projects/${id}`)
};

// Clients API
export const clientsAPI = {
  getAll: () => api.get('/clients'),
  create: (formData) => api.post('/clients', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  }),
  delete: (id) => api.delete(`/clients/${id}`)
};

// Contacts API
export const contactsAPI = {
  submit: (data) => api.post('/contacts', data),
  getAll: () => api.get('/contacts')
};

// Newsletter API
export const newsletterAPI = {
  subscribe: (email) => api.post('/newsletters', { email }),
  getAll: () => api.get('/newsletters')
};

export default api;
