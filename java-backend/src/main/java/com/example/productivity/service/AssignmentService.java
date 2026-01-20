package com.example.productivity.service;

import com.example.productivity.model.Employee;
import com.example.productivity.model.Task;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    private static final double MAX_WORKLOAD = 40.0;
    
    public double calculateEmployeeWorkload(Integer employeeId) {
        Double workload = taskRepository.calculateWorkload(employeeId);
        return workload != null ? workload : 0.0;
    }
    
    public double calculateAssignmentScore(Employee employee, Task task, double currentWorkload) {
        double workloadFactor = Math.max(0.1, 1 - (currentWorkload / MAX_WORKLOAD));
        double experienceFactor = 0.2 + (employee.getExperienceLevel() / 5.0) * 0.8;
        double efficiencyFactor = Math.min(1.0, employee.getEfficiencyScore() / 10.0);
        double difficultyHandling = Math.max(0.3, 
            1 - (task.getDifficulty() - employee.getExperienceLevel() * 2) * 0.05);
        double priorityFactor = 1 + (task.getPriority() - 3) * 0.05;
        
        return (workloadFactor * 0.4 + 
                experienceFactor * 0.3 + 
                efficiencyFactor * 0.2 + 
                difficultyHandling * 0.1) * priorityFactor;
    }
    
    @Transactional
    public Employee assignTaskToBestEmployee(Integer taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        
        List<Employee> employees = employeeRepository.findAll();
        
        Employee bestEmployee = null;
        double bestScore = -1;
        
        for (Employee employee : employees) {
            double workload = calculateEmployeeWorkload(employee.getId());
            
            if (workload < 45) {
                double score = calculateAssignmentScore(employee, task, workload);
                if (score > bestScore) {
                    bestScore = score;
                    bestEmployee = employee;
                }
            }
        }
        
        if (bestEmployee == null) {
            throw new RuntimeException("No suitable employee found");
        }
        
        task.setAssignedTo(bestEmployee.getId());
        task.setStatus("pending");
        task.setStartedAt(LocalDateTime.now());
        taskRepository.save(task);
        
        return bestEmployee;
    }
}


