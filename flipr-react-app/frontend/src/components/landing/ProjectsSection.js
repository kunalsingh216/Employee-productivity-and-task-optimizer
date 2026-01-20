import React, { useState, useEffect } from 'react';
import { projectsAPI } from '../../services/api';
import './ProjectsSection.css';

const ProjectsSection = () => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      setLoading(true);
      const response = await projectsAPI.getAll();
      setProjects(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to load projects');
      console.error('Error fetching projects:', err);
    } finally {
      setLoading(false);
    }
  };

  const getImageUrl = (imagePath) => {
    if (!imagePath) return 'https://via.placeholder.com/280x200/e0e0e0/666666?text=Project';
    if (imagePath.startsWith('http')) return imagePath;
    return `http://localhost:5000${imagePath}`;
  };

  if (loading) {
    return (
      <section id="projects" className="projects-section">
        <div className="container">
          <h2 className="section-title">Our Projects</h2>
          <div className="loading">Loading projects...</div>
        </div>
      </section>
    );
  }

  if (error) {
    return (
      <section id="projects" className="projects-section">
        <div className="container">
          <h2 className="section-title">Our Projects</h2>
          <div className="error">{error}</div>
        </div>
      </section>
    );
  }

  return (
    <section id="projects" className="projects-section">
      <div className="container">
        <h2 className="section-title">Our Projects</h2>
        <div className="projects-container">
          <div className="projects-grid">
            {projects.length === 0 ? (
              <div className="loading">No projects available yet.</div>
            ) : (
              projects.map((project) => (
                <div key={project._id} className="project-card">
                  <img
                    src={getImageUrl(project.image)}
                    alt={project.name}
                    className="project-image"
                    onError={(e) => {
                      e.target.src = 'https://via.placeholder.com/280x200/e0e0e0/666666?text=Project';
                    }}
                  />
                  <div className="project-content">
                    <h3 className="project-name">{project.name}</h3>
                    <p className="project-description">{project.description}</p>
                    <button className="read-more-btn">Read More</button>
                  </div>
                </div>
              ))
            )}
          </div>
        </div>
      </div>
    </section>
  );
};

export default ProjectsSection;
