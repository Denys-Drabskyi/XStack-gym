package org.example.mapper;

import java.util.UUID;
import javax.annotation.processing.Generated;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-18T03:23:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TrainerMapperImpl implements TrainerMapper {

    @Override
    public TrainerDto fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        TrainerDto trainerDto = new TrainerDto();

        trainerDto.setFirstName( user.getFirstName() );
        trainerDto.setLastName( user.getLastName() );
        trainerDto.setUsername( user.getUsername() );
        trainerDto.setPassword( user.getPassword() );
        trainerDto.setActive( user.isActive() );
        trainerDto.setId( user.getId() );

        return trainerDto;
    }

    @Override
    public Trainer newTrainerFromDto(TrainerDto dto) {
        if ( dto == null ) {
            return null;
        }

        TrainingType specialization = null;
        UUID userId = null;

        specialization = dto.getSpecialization();
        userId = dto.getUserId();

        Trainer trainer = new Trainer( specialization, userId );

        return trainer;
    }

    @Override
    public void updateDtoFromEntity(Trainer trainee, TrainerDto dto) {
        if ( trainee == null ) {
            return;
        }

        dto.setUserId( trainee.getUserId() );
        dto.setId( trainee.getId() );
        dto.setSpecialization( trainee.getSpecialization() );
    }

    @Override
    public void updateEntityFromEntity(Trainer from, Trainer target) {
        if ( from == null ) {
            return;
        }

        target.setSpecialization( from.getSpecialization() );
    }
}
