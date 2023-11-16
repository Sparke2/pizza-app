package com.rebrova.pizzaproject.repository;

import com.rebrova.pizzaproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByPhone(String phone);
    User findByAddress(String address);
}
