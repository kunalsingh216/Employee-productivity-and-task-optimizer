package com.example.productivity.config;

import com.example.productivity.model.Employee;
import com.example.productivity.model.Task;
import com.example.productivity.repository.EmployeeRepository;
import com.example.productivity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            // Insert sample employees
            Employee[] employees = {
                new Employee("Alice Johnson", "alice@company.com", 3, 7.5),
                new Employee("Bob Smith", "bob@company.com", 5, 8.2),
                new Employee("Carol Davis", "carol@company.com", 2, 6.8),
                new Employee("David Wilson", "david@company.com", 4, 7.9),
                new Employee("Emma Brown", "emma@company.com", 3, 8.1),
                new Employee("Frank Miller", "frank@company.com", 2, 6.5),
                new Employee("Grace Lee", "grace@company.com", 4, 8.7),
                new Employee("Henry Chen", "henry@company.com", 1, 5.8)
            };
            
            for (Employee emp : employees) {
                employeeRepository.save(emp);
            }
            
            // Insert sample tasks
            Task[] tasks = {
                new Task("Fix user login issue", "Users unable to login after recent update", 4, 3.0, 2),
                new Task("Update dashboard UI", "Modernize the main dashboard interface", 6, 8.0, 3),
                new Task("Database backup system", "Implement automated database backup", 8, 12.0, 4),
                new Task("User notification system", "Add email notifications for user actions", 5, 6.0, 3),
                new Task("Performance optimization", "Optimize slow loading pages", 9, 15.0, 5),
                new Task("Mobile responsiveness", "Make website mobile-friendly", 7, 10.0, 3),
                new Task("Security audit", "Review and fix security vulnerabilities", 10, 20.0, 5),
                new Task("API documentation", "Create comprehensive API documentation", 3, 4.0, 2),
                new Task("User testing feedback", "Implement changes based on user feedback", 4, 5.0, 3),
                new Task("Code refactoring", "Clean up legacy code components", 6, 8.0, 2),
                new Task("Integration testing", "Set up automated integration tests", 7, 12.0, 4),
                new Task("Data migration", "Migrate data to new database schema", 8, 14.0, 4)
            };
            
            for (Task task : tasks) {
                taskRepository.save(task);
            }
            
            System.out.println("Sample data inserted successfully");
        }
    }
}


