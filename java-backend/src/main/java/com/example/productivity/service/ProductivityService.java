package com.example.productivity.service;

import com.example.productivity.model.Employee;
import com.example.productivity.model.Task;
import com.example.productivity.model.TaskHistory;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskHistoryRepository;
import com.example.productivity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductivityService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskHistoryRepository taskHistoryRepository;
    
    @Autowired
    private AssignmentService assignmentService;
    
    public double calculateDifficultyAdjustment(int difficulty, int experienceLevel) {
        double baseMultiplier = 1.0;
        double difficultyFactor = (difficulty - 5) * 0.1;
        double experienceFactor = (experienceLevel - 3) * 0.05;
        return Math.max(0.5, baseMultiplier + difficultyFactor - experienceFactor);
    }
    
    @Transactional
    public void updateTaskStatus(Integer taskId, String status, Double actualHours, Integer qualityScore) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        
        task.setStatus(status);
        
        if ("completed".equals(status) && actualHours != null) {
            task.setCompletedAt(LocalDateTime.now());
            task.setActualHours(actualHours);
            
            if (task.getAssignedTo() != null) {
                Employee employee = employeeRepository.findById(task.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
                
                double timeAccuracy = task.getEstimatedHours() / actualHours;
                double adjustedAccuracy = timeAccuracy / calculateDifficultyAdjustment(
                    task.getDifficulty(), employee.getExperienceLevel());
                
                // Add to history
                TaskHistory history = new TaskHistory(
                    task.getAssignedTo(),
                    taskId,
                    task.getDifficulty(),
                    actualHours,
                    adjustedAccuracy,
                    qualityScore != null ? qualityScore : 5
                );
                taskHistoryRepository.save(history);
                
                // Update employee efficiency score
                double newEfficiency = (employee.getEfficiencyScore() * 0.8) + (adjustedAccuracy * 5 * 0.2);
                employee.setEfficiencyScore(Math.max(1.0, Math.min(10.0, newEfficiency)));
                employeeRepository.save(employee);
            }
        } else if ("in_progress".equals(status)) {
            task.setStartedAt(LocalDateTime.now());
        }
        
        taskRepository.save(task);
    }
}


