package com.example.weblab.repositories;

import com.example.weblab.entities.Project;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository {
    Project createProject(Project project);
    void updateProject(Project project);
    Project findProjectById(long id);
    void deleteProject(long id);
    List<Project> getAll();

    List<Project> getAllWithFilter(long startDate, long endDate);
}
