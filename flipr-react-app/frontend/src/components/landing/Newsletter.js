import React, { useState } from 'react';
import { newsletterAPI } from '../../services/api';
import './Newsletter.css';

const Newsletter = () => {
  const [email, setEmail] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage({ type: '', text: '' });

    try {
      await newsletterAPI.subscribe(email);
      setMessage({ type: 'success', text: 'Successfully subscribed to newsletter!' });
      setEmail('');
    } catch (error) {
      setMessage({
        type: 'error',
        text: error.response?.data?.error || 'Failed to subscribe. Please try again.'
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="newsletter-section">
      <div className="container">
        <h2 className="section-title" style={{ color: 'white' }}>Subscribe to Our Newsletter</h2>
        <p style={{ marginBottom: '1rem', opacity: 0.9, textAlign: 'center' }}>
          Stay updated with our latest projects and news
        </p>
        {message.text && (
          <div className={`newsletter-message ${message.type}`}>{message.text}</div>
        )}
        <form className="newsletter-form" onSubmit={handleSubmit}>
          <input
            type="email"
            className="newsletter-input"
            placeholder="Enter your email address"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <button type="submit" className="subscribe-btn" disabled={loading}>
            {loading ? 'Subscribing...' : 'Subscribe'}
          </button>
        </form>
      </div>
    </section>
  );
};

export default Newsletter;
