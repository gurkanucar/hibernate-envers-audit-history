package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.mapper.UserMapper;
import com.gucardev.hibernateenversaudithistory.model.User;
import com.gucardev.hibernateenversaudithistory.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDTO create(UserRequest userRequest) {
    User user = UserMapper.INSTANCE.userRequestToUser(userRequest);
    user = userRepository.save(user);
    return UserMapper.INSTANCE.userToUserDTO(user);
  }

  public List<UserDTO> getAll() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(UserMapper.INSTANCE::userToUserDTONullUserIdUsername)
        .collect(Collectors.toList());
  }

  public UserDTO update(UserRequest userRequest) {
    User user = findUserById(userRequest.getId());
    User updatedUser = UserMapper.INSTANCE.userRequestToUser(userRequest);
    BeanUtils.copyProperties(updatedUser, user, "id", "username");
    var savedUser = userRepository.save(user);
    return UserMapper.INSTANCE.userToUserDTO(savedUser);
  }

  public UserDTO getById(Long id) {
    User user = findUserById(id);
    return UserMapper.INSTANCE.userToUserDTO(user);
  }

  public void delete(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
    } else {
      throw new RuntimeException("User not found with id: " + id);
    }
  }

  User findUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }
}
