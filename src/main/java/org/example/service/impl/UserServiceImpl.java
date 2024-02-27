package org.example.service.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;

  @Override
  public boolean existsById(UUID id){
    return userDao.existsById(id);
  }
  @Override
  public void checkForUsernameAvailable(User user) {
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
