package com.example.weblab.repositories;

import com.example.weblab.dto.ProjectDto;
import com.example.weblab.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query(value = "SELECT * FROM weblab.project p where p.name LIKE '%'|| ?1 || '%' OR p.description LIKE '%'|| ?1 || '%' ", nativeQuery = true)
    List<Project> findAllWithFilter(String search);

    /*@Query(value = "SELECT Project.getId() as id, " +
            "(SELECT COUNT(*) " +
            "FROM Task t " +
            "where not t.completed and t.getProjectId() = Project.getId()) as count " +
            "FROM Project")*/
    @Query(value = "SELECT weblab.project.id as id, " +
            "(SELECT COUNT(*) " +
            "FROM weblab.task t " +
            "where t.completed = false and t.project_id = weblab.project.id) as count " +
            "FROM weblab.project", nativeQuery = true)
    List<Map<Long, Integer>> getTaskCounts();

    /*@Query(value = "SELECT weblab.project.id as id, " +
            "(SELECT COUNT(*) " +
            "FROM weblab.task t " +
            "where t.completed = false and t.project_id = weblab.project.id) as count " +
            "FROM weblab.project", nativeQuery = true)*/
}
