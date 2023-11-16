package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.UserDto;
import com.rebrova.pizzaproject.exeption.UserNotFoundException;
import com.rebrova.pizzaproject.model.User;
import com.rebrova.pizzaproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public UserDto getUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return toDTO(user.get());
    }

    @Override
    public UserDto findByName(String name) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));

        if (user.isEmpty()) {
            throw new UserNotFoundException("no such user");
        }
        return toDTO(user.get());
    }

    @Override
    public UserDto findByPhone(String phone) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByPhone(phone));

        if (user.isEmpty()) {
            throw new UserNotFoundException("no such user");
        }
        return toDTO(user.get());
    }

    @Override
    public UserDto findByAddress(String address) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByPhone(address));

        if (user.isEmpty()) {
            throw new UserNotFoundException("no such user");
        }
        return toDTO(user.get());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userRepository.save(toUser(userDto));
        return userDto;
    }

    @Override
    public void deleteUserById(Integer id) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("no such user");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateWorker(Integer id, UserDto userDTO) {
        UserDto userDB = getUserById(id);
        if (Objects.nonNull(userDTO.getName()) && !"".equalsIgnoreCase(userDTO.getName())) {
            userDB.setName(userDTO.getName());
        }
        if (Objects.nonNull(userDTO.getAddress()) && !"".equalsIgnoreCase(userDTO.getAddress())) {
            userDB.setAddress(userDTO.getAddress());
        }
        if (Objects.nonNull(userDTO.getPhone()) && !"".equalsIgnoreCase(userDTO.getPhone())) {
            userDB.setPhone(userDTO.getPhone());
        }
        if (Objects.nonNull(userDTO.getOrders())) {
            userDB.setOrders(userDTO.getOrders());
        }

        userRepository.save(toUser(userDB));
        return userDB;
    }

    @Override
    public UserDto toDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .address(user.getAddress())
                .name(user.getName())
                .phone(user.getPhone())
                .orders(user.getOrders())
                .build();
    }

    @Override
    public User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .address(userDto.getAddress())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .orders(userDto.getOrders())
                .build();
    }
}
