package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.UserDto;
import com.rebrova.pizzaproject.exeption.UserNotFoundException;
import com.rebrova.pizzaproject.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserById(Integer id) throws UserNotFoundException;

    UserDto findByName(String name) throws UserNotFoundException;
    UserDto findByPhone(String phone) throws UserNotFoundException;
    UserDto findByAddress(String address) throws UserNotFoundException;
    UserDto createUser(UserDto userDto);
    void deleteUserById(Integer id) throws UserNotFoundException;
    UserDto updateWorker(Integer id, UserDto userDTO);
    UserDto toDTO(User user);

    User toUser(UserDto userDto);
}
