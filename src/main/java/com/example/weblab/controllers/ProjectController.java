package com.example.weblab.controllers;

import com.example.weblab.dto.ProjectDto;
import com.example.weblab.entities.Project;
import com.example.weblab.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto){
        return new ResponseEntity<>(service.addProject(projectDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getProject(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") long id, @RequestBody ProjectDto project){
        return new ResponseEntity<>(service.updateProject(id, project),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") long id){
        service.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjectWithFilter(@RequestParam("search") String search){
        return new ResponseEntity<>(service.getProjectWithFilter(search),HttpStatus.OK);
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<Integer, Integer>> getCounts(){
        return new ResponseEntity<>(service.getProjectCounts(),HttpStatus.OK);
    }


}
