package com.example.productivity.controller;

import com.example.productivity.model.Employee;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskHistoryRepository;
import com.example.productivity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskHistoryRepository taskHistoryRepository;
    
    @GetMapping("/{employeeId}")
    public ResponseEntity<Map<String, Object>> getRecommendations(@PathVariable Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        // Calculate current workload
        double currentWorkload = taskRepository.findAll().stream()
            .filter(t -> employeeId.equals(t.getAssignedTo()) && 
                       ("pending".equals(t.getStatus()) || "in_progress".equals(t.getStatus())))
            .mapToDouble(t -> t.getEstimatedHours() != null ? t.getEstimatedHours() : 0.0)
            .sum();
        
        // Get recent performance (last 30 days)
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<com.example.productivity.model.TaskHistory> recentHistory = 
            taskHistoryRepository.findRecentByEmployee(employeeId, thirtyDaysAgo);
        
        Double avgAccuracy = taskHistoryRepository.getAverageTimeAccuracy(employeeId, thirtyDaysAgo);
        Double avgQuality = taskHistoryRepository.getAverageQualityScore(employeeId, thirtyDaysAgo);
        
        List<Map<String, Object>> recommendations = new ArrayList<>();
        
        // Workload recommendations
        if (currentWorkload > 35) {
            recommendations.add(Map.of(
                "type", "workload",
                "message", "Current workload is high. Consider redistributing tasks or extending deadlines.",
                "priority", "high"
            ));
        } else if (currentWorkload < 15) {
            recommendations.add(Map.of(
                "type", "workload",
                "message", "Workload is light. Consider assigning additional tasks to maximize productivity.",
                "priority", "medium"
            ));
        }
        
        // Performance recommendations
        if (avgAccuracy != null && avgAccuracy < 0.8) {
            recommendations.add(Map.of(
                "type", "efficiency",
                "message", "Tasks are taking longer than estimated. Consider providing additional support or training.",
                "priority", "high"
            ));
        }
        
        if (avgQuality != null && avgQuality < 4) {
            recommendations.add(Map.of(
                "type", "quality",
                "message", "Recent task quality scores are below average. Consider code review sessions or mentoring.",
                "priority", "high"
            ));
        }
        
        if (employee.getEfficiencyScore() > 8.5) {
            recommendations.add(Map.of(
                "type", "growth",
                "message", "Excellent performance! Consider assigning more challenging tasks or leadership opportunities.",
                "priority", "low"
            ));
        }
        
        // Experience-based recommendations
        if (employee.getExperienceLevel() < 3 && currentWorkload > 25) {
            recommendations.add(Map.of(
                "type", "development",
                "message", "As a junior member, consider balancing workload with learning opportunities.",
                "priority", "medium"
            ));
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("employee_name", employee.getName());
        result.put("current_workload", currentWorkload);
        result.put("efficiency_score", employee.getEfficiencyScore());
        result.put("experience_level", employee.getExperienceLevel());
        
        Map<String, Object> recentPerformance = new HashMap<>();
        recentPerformance.put("avg_accuracy", avgAccuracy);
        recentPerformance.put("avg_quality", avgQuality);
        recentPerformance.put("recent_tasks", recentHistory.size());
        result.put("recent_performance", recentPerformance);
        result.put("recommendations", recommendations);
        
        return ResponseEntity.ok(result);
    }
}


