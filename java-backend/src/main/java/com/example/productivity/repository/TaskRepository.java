package com.example.productivity.repository;

import com.example.productivity.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    List<Task> findByStatus(String status);
    
    @Query("SELECT t FROM Task t WHERE t.assignedTo = :employeeId AND t.status IN ('pending', 'in_progress')")
    List<Task> findActiveTasksByEmployee(@Param("employeeId") Integer employeeId);
    
    @Query("SELECT COALESCE(SUM(t.estimatedHours), 0) FROM Task t WHERE t.assignedTo = :employeeId AND t.status IN ('pending', 'in_progress')")
    Double calculateWorkload(@Param("employeeId") Integer employeeId);
}


