package com.example.productivity.controller;

import com.example.productivity.model.Task;
import com.example.productivity.repository.TaskRepository;
import com.example.productivity.service.AssignmentService;
import com.example.productivity.service.ProductivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private ProductivityService productivityService;
    
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllTasks(@RequestParam(required = false) String status) {
        List<Task> tasks = status != null ? taskRepository.findByStatus(status) : taskRepository.findAll();
        
        List<Map<String, Object>> result = tasks.stream().map(task -> {
            Map<String, Object> taskData = new HashMap<>();
            taskData.put("id", task.getId());
            taskData.put("title", task.getTitle());
            taskData.put("description", task.getDescription());
            taskData.put("difficulty", task.getDifficulty());
            taskData.put("estimated_hours", task.getEstimatedHours());
            taskData.put("actual_hours", task.getActualHours());
            taskData.put("status", task.getStatus());
            taskData.put("assigned_to", task.getAssignedTo());
            taskData.put("priority", task.getPriority());
            taskData.put("created_at", task.getCreatedAt());
            taskData.put("started_at", task.getStartedAt());
            taskData.put("completed_at", task.getCompletedAt());
            
            if (task.getAssignedEmployee() != null) {
                taskData.put("assigned_name", task.getAssignedEmployee().getName());
            }
            
            return taskData;
        }).sorted((a, b) -> {
            int priorityCompare = Integer.compare((Integer) b.get("priority"), (Integer) a.get("priority"));
            if (priorityCompare != 0) return priorityCompare;
            return ((java.time.LocalDateTime) b.get("created_at"))
                .compareTo((java.time.LocalDateTime) a.get("created_at"));
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@Valid @RequestBody Task task) {
        if (task.getPriority() == null) {
            task.setPriority(3);
        }
        if (task.getEstimatedHours() == null) {
            task.setEstimatedHours(0.0);
        }
        if (task.getDifficulty() == null) {
            task.setDifficulty(5);
        }
        if (task.getStatus() == null) {
            task.setStatus("pending");
        }
        Task saved = taskRepository.save(task);
        return ResponseEntity.ok(Map.of("id", saved.getId(), "message", "Task created successfully"));
    }
    
    @PostMapping("/{taskId}/assign")
    public ResponseEntity<Map<String, Object>> assignTask(@PathVariable Integer taskId) {
        try {
            var employee = assignmentService.assignTaskToBestEmployee(taskId);
            double score = assignmentService.calculateAssignmentScore(
                employee, 
                taskRepository.findById(taskId).orElseThrow(),
                assignmentService.calculateEmployeeWorkload(employee.getId())
            );
            
            return ResponseEntity.ok(Map.of(
                "message", "Task assigned to " + employee.getName(),
                "employee_id", employee.getId(),
                "assignment_score", String.format("%.2f", score)
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{taskId}/status")
    public ResponseEntity<Map<String, Object>> updateTaskStatus(
            @PathVariable Integer taskId,
            @RequestBody Map<String, Object> request) {
        
        String status = (String) request.get("status");
        Double actualHours = request.get("actual_hours") != null ? 
            Double.parseDouble(request.get("actual_hours").toString()) : null;
        Integer qualityScore = request.get("quality_score") != null ? 
            Integer.parseInt(request.get("quality_score").toString()) : null;
        
        try {
            productivityService.updateTaskStatus(taskId, status, actualHours, qualityScore);
            return ResponseEntity.ok(Map.of("message", "Task status updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}


