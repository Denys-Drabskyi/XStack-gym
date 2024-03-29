package org.example.service.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.configuration.security.BruteForceBlacklist;
import org.example.dao.UserDao;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public boolean existsById(UUID id) {
    return userDao.existById(id);
  }

  @Override
  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  @Override
  public User createUser(UserDto dto) {
    log.info("Started new user creation");
    User user = userMapper.toBuilder(dto)
        .username(String.format("%s.%s", dto.getFirstName(), dto.getLastName()))
        .password(passwordEncoder.encode(dto.getPassword()))
        .active(true)
        .build();
    log.info("Checking if username:{} is available", user.getUsername());
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

  @Override
  public User getByUsername(String username) {
    log.info("Getting user with username:{}", username);
    return  userDao.getByUsername(username)
        .orElseThrow(()-> EntityNotFoundException.byUsername(username, User.class.getSimpleName()));
  }

  @Override
  public void updatePassword(PasswordChangeDto dto) {
    log.info("Updating password for user with username:{}", dto.getUsername());
    User user = getByUsername(dto.getUsername());
    user.setPassword(dto.getNewPassword());
    userDao.save(user);
  }

  @Override
  public void changeActive(String username) {
    User user = getByUsername(username);
    log.info("Changing active status to:{} for user with username:{}", !user.isActive(), username);
    user.setActive(!user.isActive());
    userDao.save(user);
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
