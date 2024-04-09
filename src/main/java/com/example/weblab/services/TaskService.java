package com.example.weblab.services;

import com.example.weblab.NotFoundException;
import com.example.weblab.dto.TaskDto;
import com.example.weblab.entities.Task;
import com.example.weblab.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepository taskRepository;

    public TaskDto addTask(long projectId, TaskDto taskDto) {
        taskDto.setProjectId(projectId);
        Task ta = modelMapper.map(taskDto, Task.class);
        ta = taskRepository.save(ta);
        return modelMapper.map(ta, TaskDto.class);
    }

    public TaskDto getTask(long projectId, long taskId) {
        Task task = taskRepository.findByIdAndProjectId(projectId, taskId);
        if(task == null)
            throw new NotFoundException();
        return modelMapper.map(task, TaskDto.class);
    }

    public List<TaskDto> getAll(long projectId) {
        return taskRepository.findAllByProjectId(projectId).stream().map(task -> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public TaskDto updateTask(long projectId, long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).get();
        if(task == null)
            throw new NotFoundException();
        task.setName(taskDto.getName());
        task.setCompleted(taskDto.isCompleted());
        task.setDescription(taskDto.getDescription());
        task.setEndDate(taskDto.getEndDate());
        return modelMapper.map(task, TaskDto.class);
    }

    public void deleteTask(long projectId, long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void deleteCompletedTask(long projectId) {
        taskRepository.deleteByIdAndCompleted(projectId, true);
    }
}
