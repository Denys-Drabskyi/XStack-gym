package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "active", ignore = true)
  User newUserFromDto(UserDto dto);

  void updateEntityFromDto(UserDto from, @MappingTarget User target);
  void updateEntityFromEntity(User from, @MappingTarget User target);
}
