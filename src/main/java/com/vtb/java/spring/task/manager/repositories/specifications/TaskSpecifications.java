package com.vtb.java.spring.task.manager.repositories.specifications;

import com.vtb.java.spring.task.manager.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> titleLike(String titlePart) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Task> leaderUsernameLike(String leaderUsername) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("leader").get("username"), String.format("%%%s%%", leaderUsername));
    }

    public static Specification<Task> projectTitleLike(String projectTitle) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("project").get("title"), String.format("%%%s%%", projectTitle));
    }

    public static Specification<Task> statusEqual(String status) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), Task.Status.valueOf(status));
    }

    public static Specification<Task> priorityEqual(String priority) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("priority"), Task.Priority.valueOf(priority));
    }

    public static Specification<Task> userLike(String user) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.join("users").get("username"), String.format("%%%s%%", user));
    }

    public static Specification<Task> containsUser(String user) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.join("project").join("users").get("username"), String.format("%%%s%%", user));
    }

}
