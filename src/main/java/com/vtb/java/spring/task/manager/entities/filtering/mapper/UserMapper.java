package com.vtb.java.spring.task.manager.entities.filtering.mapper;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.filtering.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser (UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser (User User);

    List<User> toUserList (List<UserDto> userDtos);

    List<UserDto> fromUserList (List<User> users);
}
