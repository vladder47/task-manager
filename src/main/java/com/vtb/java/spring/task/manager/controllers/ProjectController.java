package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.ProjectDto;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.ProjectService;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/projects")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    @GetMapping
    public Page<ProjectDto> getAllProjectsDto(@RequestParam(value = "page", defaultValue = "1") Integer page){
        return projectService.findAllProjectDto(page - 1, 10);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id){
        return projectService.findProjectDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %d не найден", id)));
    }

    @PostMapping(path = "/create",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)

    public Project createNewProject(@RequestBody Project project) {
        if (project.getId() != null) {
            project.setId(null);
        }
        return projectService.saveOrUpdate(project);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public void modifyProject(@RequestBody Project project) {
        if (!projectService.existsById(project.getId())) {
            throw new ResourceNotFoundException(String.format("Проект с id= %d не найден", project.getId()));
        }
        projectService.saveOrUpdate(project);
    }

    @GetMapping("/create")
    public List<UserDto> getAllDtos(){
        return userService.findAllUsersDto();
    }

    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable Long id){
        projectService.deleteById(id);
    }
}
