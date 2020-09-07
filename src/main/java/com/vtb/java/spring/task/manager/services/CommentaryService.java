package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.dto.CommentaryDto;
import com.vtb.java.spring.task.manager.repositories.CommentaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentaryService {
    private CommentaryRepository commentaryRepository;

    public List<CommentaryDto> findAllCommentariesDtoByTaskId(Long id) {
        return commentaryRepository.findAllCommentariesByTaskId(id);
    }
}
