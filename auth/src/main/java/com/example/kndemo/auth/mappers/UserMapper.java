package com.example.kndemo.auth.mappers;

import com.example.kndemo.auth.dto.UserDto;
import com.example.kndemo.auth.yaml.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "token", target = "token")
    UserDto toUserDto(User user, String token);

}
