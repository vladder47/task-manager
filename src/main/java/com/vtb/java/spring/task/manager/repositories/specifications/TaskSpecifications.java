package com.vtb.java.spring.task.manager.repositories.specifications;

import com.vtb.java.spring.task.manager.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    // Этот класс пока не используется в проекте (будет использоваться при фильтрации)
    public static Specification<Task> projectEqual(Long projectId) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("project"), projectId);
    }
}
