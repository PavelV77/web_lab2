package com.example.weblab.controllers;

import com.example.weblab.dto.TaskDto;
import com.example.weblab.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskDto> addTask(@PathVariable("id") long id, @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(service.addTask(id, taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId){
        return new ResponseEntity<>(service.getTask(projectId, taskId), HttpStatus.OK);
    }
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskDto>> getAll(@PathVariable("projectId") long projectId){
        return new ResponseEntity<>(service.getAll(projectId), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId, @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(service.updateTask(projectId, taskId, taskDto), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId){
        service.deleteTask(projectId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{projectId}/tasks")
    public ResponseEntity<?> deleteCompleted(@PathVariable("projectId") long projectId){
        service.deleteCompletedTask(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
