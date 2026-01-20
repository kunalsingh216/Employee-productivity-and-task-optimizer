import React, { useState, useEffect } from 'react';
import { newsletterAPI } from '../../services/api';
import './AdminStyles.css';

const NewsletterSubscriptions = () => {
  const [subscriptions, setSubscriptions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchSubscriptions();
  }, []);

  const fetchSubscriptions = async () => {
    try {
      setLoading(true);
      const response = await newsletterAPI.getAll();
      setSubscriptions(response.data);
    } catch (error) {
      console.error('Error fetching subscriptions:', error);
    } finally {
      setLoading(false);
    }
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString();
  };

  return (
    <div className="admin-tab-content">
      <div className="form-section">
        <h2>Newsletter Subscriptions</h2>
        {loading ? (
          <div className="loading">Loading subscriptions...</div>
        ) : subscriptions.length === 0 ? (
          <div className="empty-state">No newsletter subscriptions yet.</div>
        ) : (
          <div className="table-container">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Email Address</th>
                  <th>Subscribed At</th>
                </tr>
              </thead>
              <tbody>
                {subscriptions.map((subscription) => (
                  <tr key={subscription._id}>
                    <td>{subscription.email}</td>
                    <td>{formatDate(subscription.createdAt)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default NewsletterSubscriptions;
