package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-30T04:05:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User.UserBuilder toBuilder(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder userBuilder = new User.UserBuilder();

        userBuilder.firstName( dto.getFirstName() );
        userBuilder.lastName( dto.getLastName() );
        userBuilder.username( dto.getUsername() );
        userBuilder.password( dto.getPassword() );
        userBuilder.active( dto.isActive() );

        return userBuilder;
    }

    @Override
    public void updateEntityFromDto(UserDto from, User target) {
        if ( from == null ) {
            return;
        }

        target.setFirstName( from.getFirstName() );
        target.setLastName( from.getLastName() );
        target.setPassword( from.getPassword() );
    }
}
