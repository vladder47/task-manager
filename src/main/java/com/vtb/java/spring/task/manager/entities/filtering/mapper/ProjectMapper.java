package com.vtb.java.spring.task.manager.entities.filtering.mapper;

import com.vtb.java.spring.task.manager.entities.Project;
import com.vtb.java.spring.task.manager.entities.filtering.dto.ProjectDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper MAPPER = Mappers.getMapper(ProjectMapper.class);

    Project toProject (ProjectDto projectDto);

    @InheritInverseConfiguration
    ProjectDto fromProject (Project project);

    List<Project> toProjectList (List<ProjectDto> projectDtos);

    List<ProjectDto> fromProjectList (List<Project> projects);
}
