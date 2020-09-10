package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.dto.ProjectDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<ProjectDto> findAllProjectDto() {
        return projectRepository.findAllProjectsDto();
    }

    public Project findById(Long id){
        return projectRepository.findById(id).orElseThrow();
//        return projectRepository
//                .findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %d не найден", id)));
    }

    public ProjectDto findProjectByIdDto(Long id){
        return projectRepository.findProjectByIdDto(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Проект с id = " + id + " не найден"));
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
