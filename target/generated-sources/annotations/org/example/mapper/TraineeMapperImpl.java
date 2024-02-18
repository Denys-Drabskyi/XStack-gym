package org.example.mapper;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-18T03:23:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TraineeMapperImpl implements TraineeMapper {

    @Override
    public TraineeDto fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        TraineeDto traineeDto = new TraineeDto();

        traineeDto.setFirstName( user.getFirstName() );
        traineeDto.setLastName( user.getLastName() );
        traineeDto.setUsername( user.getUsername() );
        traineeDto.setPassword( user.getPassword() );
        traineeDto.setActive( user.isActive() );
        traineeDto.setId( user.getId() );

        return traineeDto;
    }

    @Override
    public Trainee newTraineeFromDto(TraineeDto dto) {
        if ( dto == null ) {
            return null;
        }

        UUID userId = null;
        Date birthDate = null;
        String address = null;

        userId = dto.getUserId();
        birthDate = dto.getBirthDate();
        address = dto.getAddress();

        Trainee trainee = new Trainee( birthDate, address, userId );

        return trainee;
    }

    @Override
    public void updateDtoFromEntity(Trainee trainee, TraineeDto dto) {
        if ( trainee == null ) {
            return;
        }

        dto.setUserId( trainee.getUserId() );
        dto.setId( trainee.getId() );
        dto.setBirthDate( trainee.getBirthDate() );
        dto.setAddress( trainee.getAddress() );
    }

    @Override
    public void updateEntityFromEntity(Trainee from, Trainee target) {
        if ( from == null ) {
            return;
        }

        target.setBirthDate( from.getBirthDate() );
        target.setAddress( from.getAddress() );
    }
}
