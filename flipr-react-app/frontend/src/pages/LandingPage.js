import React from 'react';
import Header from '../components/landing/Header';
import Hero from '../components/landing/Hero';
import ProjectsSection from '../components/landing/ProjectsSection';
import ClientsSection from '../components/landing/ClientsSection';
import ContactForm from '../components/landing/ContactForm';
import Newsletter from '../components/landing/Newsletter';
import './LandingPage.css';

const LandingPage = () => {
  return (
    <div className="landing-page">
      <Header />
      <Hero />
      <ProjectsSection />
      <ClientsSection />
      <ContactForm />
      <Newsletter />
      <footer className="footer">
        <div className="container">
          <p>&copy; 2024 Flipr App. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
