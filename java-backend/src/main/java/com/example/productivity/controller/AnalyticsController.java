package com.example.productivity.controller;

import com.example.productivity.model.Employee;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskHistoryRepository;
import com.example.productivity.repository.TaskRepository;
import com.example.productivity.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("stats", analyticsService.getDashboardStats());
        dashboard.put("employeePerformance", analyticsService.getEmployeePerformance());
        dashboard.put("difficultyDistribution", analyticsService.getDifficultyDistribution());
        dashboard.put("priorityDistribution", analyticsService.getPriorityDistribution());
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/trends")
    public ResponseEntity<List<Map<String, Object>>> getTrends() {
        return ResponseEntity.ok(analyticsService.getProductivityTrends());
    }
}


