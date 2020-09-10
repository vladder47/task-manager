package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.ProjectDto;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.ProjectService;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
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
    public List<ProjectDto> getAllProjectsDto(){
        return projectService.findAllProjectDto();
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id){
        return projectService.findProjectByIdDto(id);
    }

    @PostMapping(path = "/create",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createNewProject(@RequestBody Project project){
        System.out.println("project = " + project);
        return projectService.saveOrUpdate(project);
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
