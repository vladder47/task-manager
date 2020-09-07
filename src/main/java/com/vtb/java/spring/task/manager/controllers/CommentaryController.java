package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.dto.CommentaryDto;
import com.vtb.java.spring.task.manager.services.CommentaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentaryController {
    private CommentaryService commentaryService;

    @GetMapping("/{id}")
    public List<CommentaryDto> findAllCommentariesDtoByTaskId(@PathVariable Long id) {
        return commentaryService.findAllCommentariesDtoByTaskId(id);
    }
}
