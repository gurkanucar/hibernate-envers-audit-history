package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.UserHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.mapper.UserHistoryMapper;
import com.gucardev.hibernateenversaudithistory.mapper.UserMapper;
import com.gucardev.hibernateenversaudithistory.model.User;
import com.gucardev.hibernateenversaudithistory.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EntityManager entityManager;

  public UserServiceImpl(UserRepository userRepository, EntityManager entityManager) {
    this.userRepository = userRepository;
    this.entityManager = entityManager;
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

  public User findUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  public List<UserHistoryDTO> getUserHistory(Long userId) {
    AuditReader auditReader = AuditReaderFactory.get(entityManager);

    List<Object[]> revisions =
        auditReader
            .createQuery()
            .forRevisionsOfEntity(User.class, false, true)
            .add(AuditEntity.id().eq(userId))
            .getResultList();

    return revisions.stream().map(this::mapToUserHistoryDTO).collect(Collectors.toList());
  }

  private UserHistoryDTO mapToUserHistoryDTO(Object[] revision) {
    User user = (User) revision[0];
    DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) revision[1];
    RevisionType revisionType = (RevisionType) revision[2];

    return UserHistoryMapper.INSTANCE.userToUserHistoryDTO(user, revisionType, revisionEntity);
  }
}
