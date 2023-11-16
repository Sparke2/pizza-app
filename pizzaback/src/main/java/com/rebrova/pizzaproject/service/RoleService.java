package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.UserDto;
import com.rebrova.pizzaproject.model.Role;

public interface RoleService {
    Role updateRole(Integer id, Role role);
    String singIn(String login, String password);
    void signOut(String login);
    Integer getInto(String login);
}
