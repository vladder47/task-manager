package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
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

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }
}
