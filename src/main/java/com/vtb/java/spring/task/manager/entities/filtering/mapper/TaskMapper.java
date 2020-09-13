package com.vtb.java.spring.task.manager.entities.filtering.mapper;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.filtering.dto.TaskFilterDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, ProjectMapper.class})
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(source = "leaderDto", target = "leader"),
            @Mapping(source = "projectDto", target = "project")
    })
    Task toTask(TaskFilterDto taskFilterDto);

    @InheritInverseConfiguration
    TaskFilterDto fromTask(Task task);

    List<Task> toTaskList(List<TaskFilterDto> taskFilterDtos);

    List<TaskFilterDto> fromTaskList(List<Task> tasks);
}
