package com.example.productivity.service;

import com.example.productivity.model.Employee;
import com.example.productivity.model.Task;
import com.example.productivity.model.TaskHistory;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskHistoryRepository;
import com.example.productivity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskHistoryRepository taskHistoryRepository;
    
    @Autowired
    private TaskRepository taskRepo;
    
    public Map<String, Object> getDashboardStats() {
        List<Task> allTasks = taskRepository.findAll();
        
        long totalTasks = allTasks.size();
        long completedTasks = allTasks.stream()
            .filter(t -> "completed".equals(t.getStatus()))
            .count();
        long activeTasks = allTasks.stream()
            .filter(t -> "in_progress".equals(t.getStatus()))
            .count();
        long pendingTasks = allTasks.stream()
            .filter(t -> "pending".equals(t.getStatus()))
            .count();
        
        double avgTimeAccuracy = allTasks.stream()
            .filter(t -> "completed".equals(t.getStatus()) && t.getActualHours() != null && t.getEstimatedHours() != null)
            .mapToDouble(t -> t.getEstimatedHours() / t.getActualHours())
            .average()
            .orElse(0.0);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("total_tasks", totalTasks);
        stats.put("completed_tasks", completedTasks);
        stats.put("active_tasks", activeTasks);
        stats.put("pending_tasks", pendingTasks);
        stats.put("avg_time_accuracy", avgTimeAccuracy);
        
        return stats;
    }
    
    public List<Map<String, Object>> getEmployeePerformance() {
        List<Employee> employees = employeeRepository.findAll();
        List<Map<String, Object>> performance = new ArrayList<>();
        
        for (Employee emp : employees) {
            List<Task> empTasks = taskRepository.findAll().stream()
                .filter(t -> emp.getId().equals(t.getAssignedTo()))
                .collect(Collectors.toList());
            
            long totalTasks = empTasks.size();
            double timeAccuracy = empTasks.stream()
                .filter(t -> "completed".equals(t.getStatus()) && t.getActualHours() != null && t.getEstimatedHours() != null)
                .mapToDouble(t -> t.getEstimatedHours() / t.getActualHours())
                .average()
                .orElse(0.0);
            
            double currentWorkload = empTasks.stream()
                .filter(t -> "pending".equals(t.getStatus()) || "in_progress".equals(t.getStatus()))
                .mapToDouble(t -> t.getEstimatedHours() != null ? t.getEstimatedHours() : 0.0)
                .sum();
            
            Map<String, Object> perf = new HashMap<>();
            perf.put("id", emp.getId());
            perf.put("name", emp.getName());
            perf.put("experience_level", emp.getExperienceLevel());
            perf.put("efficiency_score", emp.getEfficiencyScore());
            perf.put("total_tasks", totalTasks);
            perf.put("time_accuracy", timeAccuracy);
            perf.put("current_workload", currentWorkload);
            
            performance.add(perf);
        }
        
        performance.sort((a, b) -> Double.compare(
            (Double) b.get("efficiency_score"), 
            (Double) a.get("efficiency_score")
        ));
        
        return performance;
    }
    
    public List<Map<String, Object>> getDifficultyDistribution() {
        List<Task> tasks = taskRepository.findAll();
        Map<Integer, Long> distribution = tasks.stream()
            .collect(Collectors.groupingBy(Task::getDifficulty, Collectors.counting()));
        
        return distribution.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> {
                Map<String, Object> item = new HashMap<>();
                item.put("difficulty", e.getKey());
                item.put("count", e.getValue());
                return item;
            })
            .collect(Collectors.toList());
    }
    
    public List<Map<String, Object>> getPriorityDistribution() {
        List<Task> tasks = taskRepository.findAll();
        Map<Integer, Long> distribution = tasks.stream()
            .collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));
        
        return distribution.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> {
                Map<String, Object> item = new HashMap<>();
                item.put("priority", e.getKey());
                item.put("count", e.getValue());
                return item;
            })
            .collect(Collectors.toList());
    }
    
    public List<Map<String, Object>> getProductivityTrends() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<TaskHistory> history = taskHistoryRepository.findAll().stream()
            .filter(h -> h.getCreatedAt().isAfter(thirtyDaysAgo))
            .collect(Collectors.toList());
        
        Map<String, List<TaskHistory>> byDate = history.stream()
            .collect(Collectors.groupingBy(h -> h.getCreatedAt().toLocalDate().toString()));
        
        return byDate.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> {
                List<TaskHistory> dayHistory = e.getValue();
                double avgAccuracy = dayHistory.stream()
                    .mapToDouble(h -> h.getTimeAccuracy() != null ? h.getTimeAccuracy() : 0.0)
                    .average()
                    .orElse(0.0);
                double avgQuality = dayHistory.stream()
                    .mapToInt(h -> h.getQualityScore() != null ? h.getQualityScore() : 5)
                    .average()
                    .orElse(5.0);
                
                Map<String, Object> trend = new HashMap<>();
                trend.put("date", e.getKey());
                trend.put("tasks_completed", dayHistory.size());
                trend.put("avg_accuracy", avgAccuracy);
                trend.put("avg_quality", avgQuality);
                return trend;
            })
            .collect(Collectors.toList());
    }
}


