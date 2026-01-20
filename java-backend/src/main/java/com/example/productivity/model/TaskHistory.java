package com.example.productivity.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_history")
public class TaskHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "employee_id")
    private Integer employeeId;
    
    @Column(name = "task_id")
    private Integer taskId;
    
    private Integer difficulty;
    
    @Column(name = "completion_time")
    private Double completionTime;
    
    @Column(name = "time_accuracy")
    private Double timeAccuracy;
    
    @Column(name = "quality_score")
    private Integer qualityScore = 5;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Constructors
    public TaskHistory() {}
    
    public TaskHistory(Integer employeeId, Integer taskId, Integer difficulty, 
                     Double completionTime, Double timeAccuracy, Integer qualityScore) {
        this.employeeId = employeeId;
        this.taskId = taskId;
        this.difficulty = difficulty;
        this.completionTime = completionTime;
        this.timeAccuracy = timeAccuracy;
        this.qualityScore = qualityScore;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    
    public Integer getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
    public Integer getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
    
    public Double getCompletionTime() {
        return completionTime;
    }
    
    public void setCompletionTime(Double completionTime) {
        this.completionTime = completionTime;
    }
    
    public Double getTimeAccuracy() {
        return timeAccuracy;
    }
    
    public void setTimeAccuracy(Double timeAccuracy) {
        this.timeAccuracy = timeAccuracy;
    }
    
    public Integer getQualityScore() {
        return qualityScore;
    }
    
    public void setQualityScore(Integer qualityScore) {
        this.qualityScore = qualityScore;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


