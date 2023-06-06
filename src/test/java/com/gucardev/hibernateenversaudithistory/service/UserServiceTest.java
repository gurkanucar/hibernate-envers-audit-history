package com.gucardev.hibernateenversaudithistory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.model.User;
import com.gucardev.hibernateenversaudithistory.repository.UserRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest extends UserServiceTestSupport {

  @Mock private UserRepository userRepository;

  @Mock private EntityManager entityManager;

  private UserServiceImpl userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    userService = new UserServiceImpl(userRepository, entityManager);
  }

  @Test
  void testCreate_WithValidUserRequest_ReturnsUserDTO() {

    UserRequest userRequest = createUserRequest(1L, "user1");
    User user = createUser(1L, "user1");
    when(userRepository.save(any(User.class))).thenReturn(user);

    UserDTO result = userService.create(userRequest);

    assertNotNull(result);
    assertEquals(user.getId(), result.getId());
    assertEquals(user.getUsername(), result.getUsername());
    verify(userRepository, times(1)).save(any(User.class));
  }
}