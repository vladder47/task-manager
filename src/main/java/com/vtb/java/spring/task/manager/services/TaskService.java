package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.TaskDto;
import com.vtb.java.spring.task.manager.entities.filtering.dto.TaskFilterDto;
import com.vtb.java.spring.task.manager.entities.filtering.mapper.TaskMapper;
import com.vtb.java.spring.task.manager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Page<TaskDto> findAllTasksDtoByProjectId(Long projectId, int page, int size) {
        return taskRepository.findAllTasksByProjectId(projectId, PageRequest.of(page, size));
    }

    public Page<TaskFilterDto> findAllTasks(int page, int size, Specification<Task> spec, Sort sort) {
        Page<Task> pageTask = taskRepository.findAll(spec, PageRequest.of(page, size, sort));
        List<TaskFilterDto> tasks = TaskMapper.MAPPER.fromTaskList(pageTask.getContent());
        return new PageImpl<>(tasks, pageTask.getPageable(), pageTask.getTotalElements());
    }

    public List<Task> findAllTasks(Specification<Task> spec, Sort sort) {
        return taskRepository.findAll(spec, sort);
    }

    public Optional<TaskDto> findTaskDtoById(Long id) {
        return taskRepository.findTaskById(id);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveOrUpdate(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

}
