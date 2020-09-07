package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.dto.TaskDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.ProjectService;
import com.vtb.java.spring.task.manager.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;
    private ProjectService projectService;

    @GetMapping
    public Page<TaskDto> getAllTasksByProjectId(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "project") Long projectId) {
        return taskService.findAllTasksDto(projectId,page - 1, 10);
    }

//    @GetMapping("/{id}")
//    public Task getTaskById(@PathVariable Long id) {
//        return taskService.findTaskById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задачи с id = %d не найдена", id)));
//    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.findTaskDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задачи с id = %d не найдена", id)));
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
