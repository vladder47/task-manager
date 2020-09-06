package com.vtb.java.spring.task.manager.project;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.services.ProjectService;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/projects")
public class ProjectsRestController {
    private ProjectService projectService;
    private UserService userService;

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        System.out.println(projectService.findById(id));
        return projectService.findById(id);
    }

    @PostMapping(path = "/create",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createNewProject(@RequestBody Project project){
        project.setDeadline(project.getDeadline().plusHours(3L));
        return projectService.saveOrUpdate(project);
    }

    @GetMapping("/create")
    public List<User> getAllDtos(){
        System.out.println(userService.findAll());
        return userService.findAll();
    }
}
