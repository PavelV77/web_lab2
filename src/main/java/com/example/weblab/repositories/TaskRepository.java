package com.example.weblab.repositories;


import com.example.weblab.dto.TaskDto;
import com.example.weblab.entities.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query(value = "SELECT * FROM weblab.task t WHERE t.id=:taskId AND t.project_id = :inputProjectId", nativeQuery = true)
    Task findByIdAndProjectId(@Param("inputProjectId") long projectId, @Param("taskId") long taskId);

    @Query(value = "SELECT * FROM weblab.task t WHERE t.project_id = :inputProjectId", nativeQuery = true)
    List<Task> findAllByProjectId(@Param("inputProjectId") long projectId);

    @Modifying
    @Query(value = "DELETE FROM weblab.task t WHERE t.project_id = ?1 and completed = ?2", nativeQuery = true)
    void deleteByIdAndCompleted(long projectId, boolean completed);

}
