package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.dto.ProjectDto;
import com.vtb.java.spring.task.manager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Page<ProjectDto> findAllProjectDto(int page, int size) {
        return projectRepository.findAllProjectsDto(PageRequest.of(page, size));
    }

//    public Project findById(Long id){
//        return projectRepository.findById(id).orElseThrow();
////        return projectRepository
////                .findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %d не найден", id)));
//    }
//
//    public ProjectDto findProjectByIdDto(Long id){
//        return projectRepository.findProjectByIdDto(id);
////                .orElseThrow(() -> new ResourceNotFoundException("Проект с id = " + id + " не найден"));
//    }

    public Optional<ProjectDto> findProjectDtoById(Long id) {
        return projectRepository.findProjectDtoById(id);
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }
}
