package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    @Query("select t.id as id, t.title as title, t.leader.username as leaderUsername, t.description as description, " +
            "t.project.id as projectId, t.project.title as projectTitle, t.priority as priority, t.status as status, " +
            "t.deadLine as deadline from Task t where t.project.id = :id")
    Page<TaskDto> findAllTasksByProjectId(Long id, Pageable pageable);

    @Query("select t.id as id, t.title as title, t.leader.username as leaderUsername, t.description as description, " +
            "t.project.id as projectId, t.project.title as projectTitle, t.priority as priority, t.status as status, " +
            "t.deadLine as deadline from Task t where t.id = :id")
    Optional<TaskDto> findTaskById(Long id);
}
