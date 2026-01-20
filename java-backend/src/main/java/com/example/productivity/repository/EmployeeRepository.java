package com.example.productivity.repository;

import com.example.productivity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query("SELECT e FROM Employee e ORDER BY e.name")
    List<Employee> findAllOrderByName();
    
    boolean existsByEmail(String email);
}


