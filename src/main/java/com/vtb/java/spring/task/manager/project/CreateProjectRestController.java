package com.vtb.java.spring.task.manager.project;

import com.vtb.java.spring.task.manager.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("app/v1/projects/create")
public class CreateProjectRestController {
    private ProjectService projectService;

    @GetMapping
    public void createProject(){
        System.out.printf("HELLO");
    }
}
