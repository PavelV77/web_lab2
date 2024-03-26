package com.example.weblab.controllers;

import com.example.weblab.entities.Project;
import com.example.weblab.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;
    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project){
        log.info(project.toString());
        return new ResponseEntity<>(service.createProject(project), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project project,@PathVariable(name = "id") Long id){
        service.updateProject(id, project);
        return new ResponseEntity<>(service.findProjectById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "id") Long id){
        service.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.getProject(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProject(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAllWithFilter(@RequestParam("start_date") long startDate, @RequestParam("end_date") long endDate){
        return new ResponseEntity<>(service.getAllWithFilter(startDate,endDate), HttpStatus.OK);
    }

}
