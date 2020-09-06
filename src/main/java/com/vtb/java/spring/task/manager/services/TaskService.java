package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public Page<Task> findAllTasks(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size));
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Page<Task> findAllTasks(Specification<Task> spec, int page, int size) {
        return taskRepository.findAll(spec, PageRequest.of(page, size));
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
