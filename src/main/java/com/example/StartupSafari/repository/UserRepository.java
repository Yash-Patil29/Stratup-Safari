package com.example.StartupSafari.repository;

import com.example.StartupSafari.model.Role;
import com.example.StartupSafari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail(String email);

    List<User> findByRole(Role role);
}
