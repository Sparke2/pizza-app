package com.rebrova.pizzaproject.repository;

import com.rebrova.pizzaproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByLogin(String login);
    Optional<Role> findById(Integer id);
}
