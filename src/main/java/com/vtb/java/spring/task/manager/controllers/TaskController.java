package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.dto.TaskDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.TaskService;
import com.vtb.java.spring.task.manager.utils.TaskFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public Page<TaskDto> getAllTasksByProjectId(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "project") Long projectId) {
        return taskService.findAllTasksDtoByProjectId(projectId, page - 1, 10);
    }

//    @GetMapping("/all")
//    public Page<TaskDto> getAllTasks(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                     @RequestParam(required = false) Map<String, String> params) {
//        System.out.println(params);
//        TaskFilter taskFilter = new TaskFilter(params);
//        return taskService.findAllTasksDto(page - 1, 10, taskFilter.getSpec());
//    }

//    @GetMapping("/all")
//    public Page<Task> getAllTasks(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                  @RequestParam(required = false) Map<String, String> params) {
//        TaskFilter taskFilter = new TaskFilter(params);
//        return taskService.findAllTasks(page - 1, 10, taskFilter.getSpec());
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
    public void modifyTask(@RequestBody Task task) {
        if (!taskService.existsById(task.getId())) {
            throw new ResourceNotFoundException(String.format("Задача с id= %d не найдена", task.getId()));
        }
        taskService.saveOrUpdate(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@RequestParam Long id) {
        if (!taskService.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Задача с id= %d не найдена", id));
        }
        taskService.deleteById(id);
    }

    @GetMapping("/priority")
    public Map<String, String> getAllTasksPriorities() {
        Map<String, String> priorities = new HashMap<>();
        for (Task.Priority p : Task.Priority.values()) {
            priorities.put(p.toString(), p.getRus());
        }
        return priorities;
    }

    @GetMapping("/status")
    public Map<String, String> getAllTasksStatuses() {
        Map<String, String> statuses = new HashMap<>();
        for (Task.Status s : Task.Status.values()) {
            statuses.put(s.toString(), s.getRus());
        }
        return statuses;
    }
}
