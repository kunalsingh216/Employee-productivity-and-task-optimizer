import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header = () => {
  return (
    <header className="header">
      <nav className="nav">
        <div className="logo">Flipr App</div>
        <ul className="nav-links">
          <li><a href="#projects">Projects</a></li>
          <li><a href="#clients">Clients</a></li>
          <li><a href="#contact">Contact</a></li>
          <li><Link to="/admin">Admin</Link></li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
