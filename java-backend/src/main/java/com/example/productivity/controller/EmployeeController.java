package com.example.productivity.controller;

import com.example.productivity.model.Employee;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskRepository;
import com.example.productivity.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAllOrderByName();
        
        List<Map<String, Object>> result = employees.stream().map(emp -> {
            Map<String, Object> empData = new HashMap<>();
            empData.put("id", emp.getId());
            empData.put("name", emp.getName());
            empData.put("email", emp.getEmail());
            empData.put("experience_level", emp.getExperienceLevel());
            empData.put("efficiency_score", emp.getEfficiencyScore());
            empData.put("created_at", emp.getCreatedAt());
            
            // Calculate current workload
            double workload = taskRepository.findAll().stream()
                .filter(t -> emp.getId().equals(t.getAssignedTo()) && 
                           ("pending".equals(t.getStatus()) || "in_progress".equals(t.getStatus())))
                .mapToDouble(t -> t.getEstimatedHours() != null ? t.getEstimatedHours() : 0.0)
                .sum();
            empData.put("current_workload", workload);
            
            // Count completed tasks
            long completed = taskRepository.findAll().stream()
                .filter(t -> emp.getId().equals(t.getAssignedTo()) && "completed".equals(t.getStatus()))
                .count();
            empData.put("completed_tasks", completed);
            
            // Average time accuracy
            double avgAccuracy = taskRepository.findAll().stream()
                .filter(t -> emp.getId().equals(t.getAssignedTo()) && 
                           "completed".equals(t.getStatus()) && 
                           t.getActualHours() != null && t.getEstimatedHours() != null)
                .mapToDouble(t -> t.getEstimatedHours() / t.getActualHours())
                .average()
                .orElse(0.0);
            empData.put("avg_time_accuracy", avgAccuracy);
            
            return empData;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEmployee(@Valid @RequestBody Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Email already exists"));
        }
        
        if (employee.getExperienceLevel() == null) {
            employee.setExperienceLevel(1);
        }
        if (employee.getEfficiencyScore() == null) {
            employee.setEfficiencyScore(5.0);
        }
        
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("id", saved.getId(), "message", "Employee created successfully"));
    }
    
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElse(null);
        
        if (employee == null) {
            return ResponseEntity.ok(Map.of("message", "Employee already removed"));
        }
        
        // Unassign tasks
        taskRepository.findAll().stream()
            .filter(t -> employeeId.equals(t.getAssignedTo()))
            .forEach(t -> {
                t.setAssignedTo(null);
                taskRepository.save(t);
            });
        
        employeeRepository.deleteById(employeeId);
        return ResponseEntity.ok(Map.of("message", "Employee removed successfully"));
    }
}


