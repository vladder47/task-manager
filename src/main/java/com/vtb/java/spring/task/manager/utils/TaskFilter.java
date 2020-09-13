package com.vtb.java.spring.task.manager.utils;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.repositories.specifications.TaskSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

@Getter
public class TaskFilter {
    private Specification<Task> spec;

    public TaskFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("title") && params.get("title") != null) {
            spec = spec.and(TaskSpecifications.titleLike(params.get("title").get(0)));
        }
        if (params.containsKey("leader") && params.get("leader") != null) {
            spec = spec.and(TaskSpecifications.leaderUsernameLike(params.get("leader").get(0)));
        }
        if (params.containsKey("project") && params.get("project") != null) {
            spec = spec.and(TaskSpecifications.projectTitleLike(params.get("project").get(0)));
        }
        if (params.containsKey("user") && params.get("user") != null) {
            spec = spec.and(TaskSpecifications.userLike(params.get("user").get(0)));
        }
        if (params.containsKey("auth") && params.get("auth") != null) {
            spec = spec.and(TaskSpecifications.containsUser(params.get("auth").get(0)));
        }
        if (params.containsKey("status") && params.get("status") != null) {
            Specification<Task> statusSpec = null;
            for (String status : params.get("status")) {
                if (statusSpec == null) {
                    statusSpec = TaskSpecifications.statusEqual(status);
                } else {
                    statusSpec = statusSpec.or(TaskSpecifications.statusEqual(status));
                }
            }
            spec = spec.and(statusSpec);
        }
        if (params.containsKey("priority") && params.get("priority") != null) {
            Specification<Task> prioritySpec = null;
            for (String priority : params.get("priority")) {
                if (prioritySpec == null) {
                    prioritySpec = TaskSpecifications.priorityEqual(priority);
                } else {
                    prioritySpec = prioritySpec.or(TaskSpecifications.priorityEqual(priority));
                }
            }
            spec = spec.and(prioritySpec);
        }
    }
}
