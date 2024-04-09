package com.example.weblab.services;

import com.example.weblab.NotFoundException;
import com.example.weblab.dto.ProjectDto;
import com.example.weblab.entities.Project;
import com.example.weblab.repositories.ProjectRepository;
import org.aspectj.asm.IModelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public ProjectDto addProject(ProjectDto projectDto) {
        return mapToDto(projectRepository.save(mapToEntity(projectDto)));
    }

    public ProjectDto getProject(long id) {
        Project project = projectRepository.findById(id).get();
        if(project == null)
            throw new NotFoundException();
        return mapToDto(project);
    }

    @Transactional
    public ProjectDto updateProject(long id, ProjectDto dto) {
        Project projectFromDb = projectRepository.findById(id).get();
        if(projectFromDb == null)
            throw new NotFoundException();
        projectFromDb.setName(dto.getName());
        projectFromDb.setStartDate(dto.getStartDate());
        projectFromDb.setEndDate(dto.getEndDate());
        return mapToDto(projectFromDb);
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDto mapToDto(Project project){
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        return dto;
    }

    private Project mapToEntity(ProjectDto dto){
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        return project;
    }

    public List<ProjectDto> getProjectWithFilter(String filter) {
        if(filter == null) {
            List<Project> ret = new ArrayList();
            projectRepository.findAll().forEach(ret::add);
            return ret.stream().map(this::mapToDto).collect(Collectors.toList());
        }
        else
            return projectRepository.findAllWithFilter(filter).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Map<Integer, Integer> getProjectCounts() {
        return projectRepository.getTaskCounts().stream().collect(Collectors.toMap(
                tuple -> ((Number) tuple.get("id")).intValue(),
                tuple -> ((Number) tuple.get("count")).intValue()
        ));
    }
}
