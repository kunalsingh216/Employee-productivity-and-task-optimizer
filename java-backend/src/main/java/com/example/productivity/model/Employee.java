package com.example.productivity.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "experience_level")
    private Integer experienceLevel = 1;
    
    @Column(name = "efficiency_score")
    private Double efficiencyScore = 5.0;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Constructors
    public Employee() {}
    
    public Employee(String name, String email, Integer experienceLevel, Double efficiencyScore) {
        this.name = name;
        this.email = email;
        this.experienceLevel = experienceLevel;
        this.efficiencyScore = efficiencyScore;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getExperienceLevel() {
        return experienceLevel;
    }
    
    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
    
    public Double getEfficiencyScore() {
        return efficiencyScore;
    }
    
    public void setEfficiencyScore(Double efficiencyScore) {
        this.efficiencyScore = efficiencyScore;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


