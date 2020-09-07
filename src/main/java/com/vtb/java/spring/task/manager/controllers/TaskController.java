package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.ProjectService;
import com.vtb.java.spring.task.manager.services.TaskService;
import com.vtb.java.spring.task.manager.utils.TaskFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;
    private ProjectService projectService;

    @GetMapping
    public Page<Task> getAllTasksByProjectId(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "project") Long projectId) {
        TaskFilter taskFilter = new TaskFilter(projectId);
        return taskService.findAllTasks(taskFilter.getSpec(), page - 1, 10);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задачи с id = %d не найдена", id)));
    }

    @GetMapping("/create/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.findProjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %d не найден", id)));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createNewTask(@RequestBody Task task) {
        if (task.getId() != null) {
            task.setId(null);
        }
        return taskService.saveOrUpdate(task);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Task modifyTask(@RequestBody Task task) {
        if (!taskService.existsById(task.getId())) {
            throw new ResourceNotFoundException(String.format("Задача с id= %d не найдена", task.getId()));
        }
        return taskService.saveOrUpdate(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@RequestParam Long id) {
        taskService.deleteById(id);
    }
}
