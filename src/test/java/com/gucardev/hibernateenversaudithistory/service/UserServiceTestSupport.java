package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.model.User;

public abstract class UserServiceTestSupport {
  protected UserRequest createUserRequest(Long id, String username) {
    UserRequest userRequest = new UserRequest();
    userRequest.setId(id);
    userRequest.setUsername(username);
    return userRequest;
  }

  protected User createUser(Long id, String username) {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    return user;
  }
}
