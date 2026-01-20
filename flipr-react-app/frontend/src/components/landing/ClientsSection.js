import React, { useState, useEffect } from 'react';
import { clientsAPI } from '../../services/api';
import './ClientsSection.css';

const ClientsSection = () => {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchClients();
  }, []);

  const fetchClients = async () => {
    try {
      setLoading(true);
      const response = await clientsAPI.getAll();
      setClients(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to load clients');
      console.error('Error fetching clients:', err);
    } finally {
      setLoading(false);
    }
  };

  const getImageUrl = (imagePath) => {
    if (!imagePath) return 'https://via.placeholder.com/120/ff6b35/ffffff?text=Client';
    if (imagePath.startsWith('http')) return imagePath;
    return `http://localhost:5000${imagePath}`;
  };

  if (loading) {
    return (
      <section id="clients" className="clients-section">
        <div className="container">
          <h2 className="section-title">Happy Clients</h2>
          <div className="loading">Loading clients...</div>
        </div>
      </section>
    );
  }

  if (error) {
    return (
      <section id="clients" className="clients-section">
        <div className="container">
          <h2 className="section-title">Happy Clients</h2>
          <div className="error">{error}</div>
        </div>
      </section>
    );
  }

  return (
    <section id="clients" className="clients-section">
      <div className="container">
        <h2 className="section-title">Happy Clients</h2>
        <div className="clients-grid">
          {clients.length === 0 ? (
            <div className="loading">No clients available yet.</div>
          ) : (
            clients.map((client) => (
              <div key={client._id} className="client-card">
                <img
                  src={getImageUrl(client.image)}
                  alt={client.name}
                  className="client-image"
                  onError={(e) => {
                    e.target.src = 'https://via.placeholder.com/120/ff6b35/ffffff?text=Client';
                  }}
                />
                <p className="client-description">"{client.description}"</p>
                <h4 className="client-name">{client.name}</h4>
                <p className="client-designation">{client.designation}</p>
              </div>
            ))
          )}
        </div>
      </div>
    </section>
  );
};

export default ClientsSection;
