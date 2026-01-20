import React, { useState, useEffect } from 'react';
import { contactsAPI } from '../../services/api';
import './AdminStyles.css';

const ContactDetails = () => {
  const [contacts, setContacts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchContacts();
  }, []);

  const fetchContacts = async () => {
    try {
      setLoading(true);
      const response = await contactsAPI.getAll();
      setContacts(response.data);
    } catch (error) {
      console.error('Error fetching contacts:', error);
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
        <h2>Contact Form Submissions</h2>
        {loading ? (
          <div className="loading">Loading contacts...</div>
        ) : contacts.length === 0 ? (
          <div className="empty-state">No contact form submissions yet.</div>
        ) : (
          <div className="table-container">
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Full Name</th>
                  <th>Email</th>
                  <th>Mobile Number</th>
                  <th>City</th>
                  <th>Submitted At</th>
                </tr>
              </thead>
              <tbody>
                {contacts.map((contact) => (
                  <tr key={contact._id}>
                    <td>{contact.fullName}</td>
                    <td>{contact.email}</td>
                    <td>{contact.mobileNumber}</td>
                    <td>{contact.city}</td>
                    <td>{formatDate(contact.createdAt)}</td>
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

export default ContactDetails;
