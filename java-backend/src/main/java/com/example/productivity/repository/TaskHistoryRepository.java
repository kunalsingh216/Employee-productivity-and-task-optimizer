package com.example.productivity.repository;

import com.example.productivity.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {
    
    @Query("SELECT th FROM TaskHistory th WHERE th.employeeId = :employeeId AND th.createdAt > :since")
    List<TaskHistory> findRecentByEmployee(@Param("employeeId") Integer employeeId, 
                                          @Param("since") LocalDateTime since);
    
    @Query("SELECT AVG(th.timeAccuracy) FROM TaskHistory th WHERE th.employeeId = :employeeId AND th.createdAt > :since")
    Double getAverageTimeAccuracy(@Param("employeeId") Integer employeeId, 
                                  @Param("since") LocalDateTime since);
    
    @Query("SELECT AVG(th.qualityScore) FROM TaskHistory th WHERE th.employeeId = :employeeId AND th.createdAt > :since")
    Double getAverageQualityScore(@Param("employeeId") Integer employeeId, 
                                  @Param("since") LocalDateTime since);
}


