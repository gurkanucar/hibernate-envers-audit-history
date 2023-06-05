package com.gucardev.hibernateenversaudithistory.repository;

import com.gucardev.hibernateenversaudithistory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
