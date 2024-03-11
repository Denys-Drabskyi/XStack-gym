package org.example.service.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.UserDao;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;
  @Autowired
  private UserMapper userMapper;

  @Override
  public boolean existsById(UUID id) {
    return userDao.existById(id);
  }

  @Override
  public User createUser(UserDto dto) {
    User user = userMapper.toBuilder(dto)
        .username(String.format("%s.%s", dto.getFirstName(), dto.getLastName()))
        .password(PasswordGenerator.generatePassword())
        .active(true)
        .build();
    log.info("Checking if username:{} is available", dto.getUsername());
    updateUsernameIfAlreadyExists(user);
    return userDao.save(user);
  }

  @Override
  public void update(UserDto dto, User user) {
    log.info("Updating user with id:{}", user.getId());
    userMapper.updateEntityFromDto(dto, user);
    userDao.save(user);
    log.info("Updated user with id:{}", user.getId());
  }

  public User getByUsername(String username){
    return  userDao.getByUsername(username)
        .orElseThrow(()-> EntityNotFoundException.byUsername(username, User.class.getSimpleName()));
  }

  @Override
  public void updatePassword(PasswordChangeDto dto){
    User user = getByUsername(dto.getUsername());
    user.setPassword(dto.getNewPassword());
    userDao.save(user);
  }

  @Override
  public void changeActive(UserCredentialsDto dto){
    User user = getByUsername(dto.getUsername());
    user.setActive(!user.isActive());
    userDao.save(user);
  }

  @Override
  public boolean auth(UserCredentialsDto dto) {
    return userDao.existsByUsernameAndPasswordAndActive(dto.getUsername(), dto.getPassword(), true);
  }

  private void updateUsernameIfAlreadyExists(User user) {
    userDao.getLastWithUserNamePattern(user.getUsername())
        .ifPresent(last -> updateSuffix(last.getUsername(), user));
  }

  private void updateSuffix(String lastUsername, User user){
    log.info(
        "Adding suffix to username:{}, username with largest suffix found:{}",
        user.getUsername(),
        lastUsername
    );
    user.addSuffixToUserNameAfter(lastUsername);
    log.info("New username:{}", user.getUsername());
  }
}
