package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.UserCredentialsDto;
import org.example.util.PasswordGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserService userService;
  private final TraineeService traineeService;
  private final TrainerService trainerService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public String signIn(UserCredentialsDto request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
    ));
    var user = userService
        .userDetailsService()
        .loadUserByUsername(request.getUsername());

    return jwtService.generateToken(user);
  }

  public UserCredentialsDto createTrainee(TraineeDto traineeDto) {
    String password = PasswordGenerator.generatePassword();
    traineeDto.setPassword(password);
    traineeDto = traineeService.create(traineeDto);
    String jwt = jwtService.generateToken(
        userService.getByUsername(traineeDto.getUsername())
    );
    traineeDto.setPassword(password);
    traineeDto.setJwt(jwt);
    return traineeDto;
  }

  public UserCredentialsDto createTrainer(TrainerDto trainerDto) {
    String password = PasswordGenerator.generatePassword();
    trainerDto.setPassword(password);
    trainerDto = trainerService.create(trainerDto);
    String jwt = jwtService.generateToken(
        userService.getByUsername(trainerDto.getUsername())
    );
    trainerDto.setPassword(password);
    trainerDto.setJwt(jwt);
    return trainerDto;
  }
}