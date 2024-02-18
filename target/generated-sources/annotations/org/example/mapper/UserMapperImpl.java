package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-18T03:23:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User newUserFromDto(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;

        firstName = dto.getFirstName();
        lastName = dto.getLastName();

        User user = new User( firstName, lastName );

        return user;
    }

    @Override
    public void updateEntityFromDto(UserDto from, User target) {
        if ( from == null ) {
            return;
        }

        target.setFirstName( from.getFirstName() );
        target.setLastName( from.getLastName() );
        target.setPassword( from.getPassword() );
        target.setActive( from.isActive() );
    }

    @Override
    public void updateEntityFromEntity(User from, User target) {
        if ( from == null ) {
            return;
        }

        target.setFirstName( from.getFirstName() );
        target.setLastName( from.getLastName() );
        target.setPassword( from.getPassword() );
        target.setActive( from.isActive() );
    }
}
