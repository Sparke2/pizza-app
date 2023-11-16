package com.rebrova.pizzaproject.controller;

import com.rebrova.pizzaproject.dtos.UserDto;
import com.rebrova.pizzaproject.exeption.UserNotFoundException;
import com.rebrova.pizzaproject.model.User;
import com.rebrova.pizzaproject.repository.UserRepository;
import com.rebrova.pizzaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Integer id) throws UserNotFoundException{
        return userService.getUserById(id);
    }
    @GetMapping("/findByName")
    public UserDto findByName(@RequestParam("name") String name) throws UserNotFoundException{
        return userService.findByName(name);
    }
    @GetMapping("/findByPhone")
    public UserDto findByPhone(@RequestParam("phone") String phone) throws UserNotFoundException{
        return userService.findByPhone(phone);
    }
    @GetMapping("/findByAddress")
    public UserDto findByAddress(@RequestParam("address") String address) throws UserNotFoundException{
        return userService.findByAddress(address);
    }
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) throws UserNotFoundException{
        userService.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public UserDto updateWorker(@PathVariable("id") Integer id, @RequestBody UserDto userDTO){
        return userService.updateWorker(id, userDTO);
    }


}
