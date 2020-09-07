package com.vtb.java.spring.task.manager.utils;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.repositories.specifications.TaskSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
public class TaskFilter {
    // тоже не используется
    private Specification<Task> spec;

    public TaskFilter(Long projectId) {
        spec = Specification.where(null);
        spec = spec.and(TaskSpecifications.projectEqual(projectId));
    }
}
