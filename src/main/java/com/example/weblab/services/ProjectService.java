package com.example.weblab.services;

import com.example.weblab.entities.Project;
import com.example.weblab.repositories.ProjectRepository;
import com.example.weblab.repositories.impl.ProjectRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project createProject(Project project){
        return projectRepository.createProject(project);
    }

    public Project findProjectById(long id){
        return projectRepository.findProjectById(id);
    }

    @Transactional
    public void updateProject(long id, Project project){
        Project projectFromDb = findProjectById(id);
        projectFromDb.setName(project.getName());
        projectFromDb.setStartDate(project.getStartDate());
        projectFromDb.setStartDate(project.getStartDate());
        projectRepository.updateProject(projectFromDb);
    }

    public void deleteProject(long id){
        projectRepository.deleteProject(id);
    }

    public Project getProject(long id){
        return projectRepository.findProjectById(id);
    }

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public List<Project> getAllWithFilter(long startDate, long endDate) {
        return projectRepository.getAllWithFilter(startDate,endDate);
    }
}
