package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.Commentary;
import com.vtb.java.spring.task.manager.entities.dto.CommentaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    @Query("select c.id as id, c.text as text, c.parent as parent, c.task.id as taskId, " +
            "c.user.username as username, c.createdAt as createdAt from Commentary c where c.task.id = :id")
    List<CommentaryDto> findAllCommentariesByTaskId(Long id);
}
