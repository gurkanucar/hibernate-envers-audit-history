package com.gucardev.hibernateenversaudithistory.controller;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.UserHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public UserDTO create(@RequestBody UserRequest request) {
    return userService.create(request);
  }

  @GetMapping
  public List<UserDTO> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public UserDTO getById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @PutMapping()
  public UserDTO update(@RequestBody UserRequest request) {
    return userService.update(request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  @GetMapping("/revision/{id}")
  public List<UserHistoryDTO> getUserRevisions(@PathVariable Long id) {
    return userService.getUserHistory(id);
  }
}
