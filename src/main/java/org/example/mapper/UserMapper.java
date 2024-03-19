package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User.UserBuilder toBuilder(UserDto dto);

  @Mapping(target = "active", ignore = true)
  void updateEntityFromDto(UserDto from, @MappingTarget User target);
}
