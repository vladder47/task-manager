package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Commentary;
import com.vtb.java.spring.task.manager.entities.dto.CommentaryDto;
import com.vtb.java.spring.task.manager.services.CommentaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Commentary createNewCommentary(@RequestBody Commentary commentary) {
        if (commentary.getId() != null) {
            commentary.setId(null);
        }
        return commentaryService.saveOrUpdate(commentary);
    }
}
