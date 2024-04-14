package org.example.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.dto.TrainingDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T12:47:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TrainingMapperImpl implements TrainingMapper {

    @Autowired
    private TrainingTypeMapper trainingTypeMapper;

    @Override
    public Training toEntity(TrainingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Training.TrainingBuilder training = Training.builder();

        training.trainee( trainingDtoToTrainee( dto ) );
        training.trainer( trainingDtoToTrainer( dto ) );
        training.type( trainingTypeMapper.toType( dto.getTrainingType() ) );
        training.name( dto.getName() );
        if ( dto.getDate() != null ) {
            training.date( Date.from( dto.getDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        training.duration( dto.getDuration() );

        return training.build();
    }

    @Override
    public TrainingDto toDto(Training entity) {
        if ( entity == null ) {
            return null;
        }

        TrainingDto.TrainingDtoBuilder trainingDto = TrainingDto.builder();

        trainingDto.trainingType( trainingTypeMapper.toName( entity.getType() ) );
        trainingDto.traineeUsername( entityTraineeUserUsername( entity ) );
        trainingDto.trainerUsername( entityTrainerUserUsername( entity ) );
        trainingDto.name( entity.getName() );
        if ( entity.getDate() != null ) {
            trainingDto.date( LocalDateTime.ofInstant( entity.getDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        trainingDto.duration( entity.getDuration() );

        return trainingDto.build();
    }

    @Override
    public List<TrainingDto> toDtoList(List<Training> entity) {
        if ( entity == null ) {
            return null;
        }

        List<TrainingDto> list = new ArrayList<TrainingDto>( entity.size() );
        for ( Training training : entity ) {
            list.add( toDto( training ) );
        }

        return list;
    }

    protected User trainingDtoToUser(TrainingDto trainingDto) {
        if ( trainingDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( trainingDto.getTraineeUsername() );

        return user.build();
    }

    protected Trainee trainingDtoToTrainee(TrainingDto trainingDto) {
        if ( trainingDto == null ) {
            return null;
        }

        Trainee.TraineeBuilder trainee = Trainee.builder();

        trainee.user( trainingDtoToUser( trainingDto ) );

        return trainee.build();
    }

    protected User trainingDtoToUser1(TrainingDto trainingDto) {
        if ( trainingDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( trainingDto.getTrainerUsername() );

        return user.build();
    }

    protected Trainer trainingDtoToTrainer(TrainingDto trainingDto) {
        if ( trainingDto == null ) {
            return null;
        }

        Trainer.TrainerBuilder trainer = Trainer.builder();

        trainer.user( trainingDtoToUser1( trainingDto ) );

        return trainer.build();
    }

    private String entityTraineeUserUsername(Training training) {
        if ( training == null ) {
            return null;
        }
        Trainee trainee = training.getTrainee();
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

    private String entityTrainerUserUsername(Training training) {
        if ( training == null ) {
            return null;
        }
        Trainer trainer = training.getTrainer();
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
}
