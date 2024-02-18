package org.example.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
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
  public Optional<User> getById(UUID id) {
    return userDao.get(id);
  }

  @Override
  public User getExistingById(UUID id) {
    return userDao.get(id).orElseThrow(
        () -> EntityNotFoundException.byId(id, User.class.getSimpleName())
    );
  }

  @Override
  public User create(User user) {
    userDao.getLastWithUserNamePattern(user.getUsername())
        .ifPresent(last -> updateSuffix(last.getUsername(), user));
    return userDao.save(user);
  }

  @Override
  public User create(UserDto userDto) {
    return create(userMapper.newUserFromDto(userDto));
  }

  @Override
  public User update(User user) {
    User storedUser = getExistingById(user.getId());
    userMapper.updateEntityFromEntity(user, storedUser);
    return userDao.save(storedUser);
  }

  @Override
  public User update(UserDto userDto) {
    User storedUser = getExistingById(userDto.getUserId());
    userMapper.updateEntityFromDto(userDto, storedUser);
    return userDao.save(storedUser);
  }

  @Override
  public void deleteById(UUID id) {
    userDao.delete(id);
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
