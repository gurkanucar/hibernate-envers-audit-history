package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.UserHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.model.User;
import java.util.List;

public interface UserService {

  UserDTO create(UserRequest userRequest);

  List<UserDTO> getAll();

  UserDTO update(UserRequest userRequest);

  UserDTO getById(Long id);

  void delete(Long id);

  User findUserById(Long id);

  List<UserHistoryDTO> getUserHistory(Long userId);
}
