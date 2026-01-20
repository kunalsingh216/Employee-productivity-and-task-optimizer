import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import ProjectManagement from '../components/admin/ProjectManagement';
import ClientManagement from '../components/admin/ClientManagement';
import ContactDetails from '../components/admin/ContactDetails';
import NewsletterSubscriptions from '../components/admin/NewsletterSubscriptions';
import './AdminPanel.css';

const AdminPanel = () => {
  const [activeTab, setActiveTab] = useState('projects');

  const tabs = [
    { id: 'projects', label: 'Projects' },
    { id: 'clients', label: 'Clients' },
    { id: 'contacts', label: 'Contact Forms' },
    { id: 'newsletters', label: 'Newsletters' }
  ];

  const renderTabContent = () => {
    switch (activeTab) {
      case 'projects':
        return <ProjectManagement />;
      case 'clients':
        return <ClientManagement />;
      case 'contacts':
        return <ContactDetails />;
      case 'newsletters':
        return <NewsletterSubscriptions />;
      default:
        return <ProjectManagement />;
    }
  };

  return (
    <div className="admin-panel">
      <div className="admin-header">
        <h1>Admin Panel</h1>
        <Link to="/">← Back to Landing Page</Link>
      </div>

      <div className="admin-container">
        <div className="tabs">
          {tabs.map((tab) => (
            <button
              key={tab.id}
              className={`tab ${activeTab === tab.id ? 'active' : ''}`}
              onClick={() => setActiveTab(tab.id)}
            >
              {tab.label}
            </button>
          ))}
        </div>

        {renderTabContent()}
      </div>
    </div>
  );
};

export default AdminPanel;
