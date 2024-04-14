package org.example.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-30T04:05:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TraineeTrainerMapperImpl implements TraineeTrainerMapper {

    @Autowired
    private TrainingTypeMapper trainingTypeMapper;

    @Override
    public Trainer.TrainerBuilder toBuilder(TrainerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Trainer.TrainerBuilder trainerBuilder = new Trainer.TrainerBuilder();

        trainerBuilder.id( dto.getId() );
        trainerBuilder.specialization( trainingTypeMapper.toType( dto.getSpecialization() ) );

        return trainerBuilder;
    }

    @Override
    public TrainerDto toDto(Trainer entity) {
        if ( entity == null ) {
            return null;
        }

        TrainerDto.TrainerDtoBuilder<?, ?> trainerDto = TrainerDto.builder();

        trainerDto.username( entityUserUsername( entity ) );
        trainerDto.firstName( entityUserFirstName( entity ) );
        trainerDto.lastName( entityUserLastName( entity ) );
        trainerDto.active( entityUserActive( entity ) );
        trainerDto.id( entity.getId() );
        trainerDto.specialization( trainingTypeMapper.toName( entity.getSpecialization() ) );

        return trainerDto.build();
    }

    @Override
    public UserCredentialsDto toCredentials(Trainer entity) {
        if ( entity == null ) {
            return null;
        }

        UserCredentialsDto.UserCredentialsDtoBuilder<?, ?> userCredentialsDto = UserCredentialsDto.builder();

        userCredentialsDto.username( entityUserUsername( entity ) );
        userCredentialsDto.password( entityUserPassword( entity ) );

        return userCredentialsDto.build();
    }

    @Override
    public TrainerDtoWithTrainees toDtoWithTrainees(Trainer entity) {
        if ( entity == null ) {
            return null;
        }

        TrainerDtoWithTrainees.TrainerDtoWithTraineesBuilder<?, ?> trainerDtoWithTrainees = TrainerDtoWithTrainees.builder();

        trainerDtoWithTrainees.username( entityUserUsername( entity ) );
        trainerDtoWithTrainees.firstName( entityUserFirstName( entity ) );
        trainerDtoWithTrainees.lastName( entityUserLastName( entity ) );
        trainerDtoWithTrainees.active( entityUserActive( entity ) );
        trainerDtoWithTrainees.id( entity.getId() );
        trainerDtoWithTrainees.specialization( trainingTypeMapper.toName( entity.getSpecialization() ) );
        trainerDtoWithTrainees.trainees( traineeListToTraineeDtoList( entity.getTrainees() ) );

        return trainerDtoWithTrainees.build();
    }

    @Override
    public void updateEntityFromDto(TrainerDto from, Trainer target) {
        if ( from == null ) {
            return;
        }
    }

    @Override
    public List<TrainerDto> toDtoList(List<Trainer> trainers) {
        if ( trainers == null ) {
            return null;
        }

        List<TrainerDto> list = new ArrayList<TrainerDto>( trainers.size() );
        for ( Trainer trainer : trainers ) {
            list.add( toDto( trainer ) );
        }

        return list;
    }

    @Override
    public Trainee.TraineeBuilder toBuilder(TraineeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Trainee.TraineeBuilder traineeBuilder = new Trainee.TraineeBuilder();

        traineeBuilder.id( dto.getId() );
        traineeBuilder.birthDate( dto.getBirthDate() );
        traineeBuilder.address( dto.getAddress() );

        return traineeBuilder;
    }

    @Override
    public TraineeDto toDto(Trainee entity) {
        if ( entity == null ) {
            return null;
        }

        TraineeDto.TraineeDtoBuilder<?, ?> traineeDto = TraineeDto.builder();

        traineeDto.username( entityUserUsername1( entity ) );
        traineeDto.password( entityUserPassword1( entity ) );
        traineeDto.firstName( entityUserFirstName1( entity ) );
        traineeDto.lastName( entityUserLastName1( entity ) );
        traineeDto.active( entityUserActive1( entity ) );
        traineeDto.id( entity.getId() );
        traineeDto.birthDate( entity.getBirthDate() );
        traineeDto.address( entity.getAddress() );

        return traineeDto.build();
    }

    @Override
    public TraineeDtoWithTrainers toDtoWithTrainers(Trainee entity) {
        if ( entity == null ) {
            return null;
        }

        TraineeDtoWithTrainers.TraineeDtoWithTrainersBuilder<?, ?> traineeDtoWithTrainers = TraineeDtoWithTrainers.builder();

        traineeDtoWithTrainers.username( entityUserUsername1( entity ) );
        traineeDtoWithTrainers.password( entityUserPassword1( entity ) );
        traineeDtoWithTrainers.firstName( entityUserFirstName1( entity ) );
        traineeDtoWithTrainers.lastName( entityUserLastName1( entity ) );
        traineeDtoWithTrainers.active( entityUserActive1( entity ) );
        traineeDtoWithTrainers.id( entity.getId() );
        traineeDtoWithTrainers.birthDate( entity.getBirthDate() );
        traineeDtoWithTrainers.address( entity.getAddress() );
        traineeDtoWithTrainers.trainers( toDtoList( entity.getTrainers() ) );

        return traineeDtoWithTrainers.build();
    }

    @Override
    public void updateEntityFromDto(TraineeDto from, Trainee target) {
        if ( from == null ) {
            return;
        }

        target.setBirthDate( from.getBirthDate() );
        target.setAddress( from.getAddress() );
    }

    private String entityUserUsername(Trainer trainer) {
        if ( trainer == null ) {
            return null;
        }
        User user = trainer.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String entityUserFirstName(Trainer trainer) {
        if ( trainer == null ) {
            return null;
        }
        User user = trainer.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityUserLastName(Trainer trainer) {
        if ( trainer == null ) {
            return null;
        }
        User user = trainer.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private boolean entityUserActive(Trainer trainer) {
        if ( trainer == null ) {
            return false;
        }
        User user = trainer.getUser();
        if ( user == null ) {
            return false;
        }
        boolean active = user.isActive();
        return active;
    }

    private String entityUserPassword(Trainer trainer) {
        if ( trainer == null ) {
            return null;
        }
        User user = trainer.getUser();
        if ( user == null ) {
            return null;
        }
        String password = user.getPassword();
        if ( password == null ) {
            return null;
        }
        return password;
    }

    protected List<TraineeDto> traineeListToTraineeDtoList(List<Trainee> list) {
        if ( list == null ) {
            return null;
        }

        List<TraineeDto> list1 = new ArrayList<TraineeDto>( list.size() );
        for ( Trainee trainee : list ) {
            list1.add( toDto( trainee ) );
        }

        return list1;
    }

    private String entityUserUsername1(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }
        User user = trainee.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String entityUserPassword1(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }
        User user = trainee.getUser();
        if ( user == null ) {
            return null;
        }
        String password = user.getPassword();
        if ( password == null ) {
            return null;
        }
        return password;
    }

    private String entityUserFirstName1(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }
        User user = trainee.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityUserLastName1(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }
        User user = trainee.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private boolean entityUserActive1(Trainee trainee) {
        if ( trainee == null ) {
            return false;
        }
        User user = trainee.getUser();
        if ( user == null ) {
            return false;
        }
        boolean active = user.isActive();
        return active;
    }
}
