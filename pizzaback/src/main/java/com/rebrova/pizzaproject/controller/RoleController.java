package com.rebrova.pizzaproject.controller;

import com.rebrova.pizzaproject.service.RoleService;
import com.rebrova.pizzaproject.service.UserService;
import org.postgresql.plugin.AuthenticationRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

//    @PostMapping("singIn")
//    public String singIn(AuthenticationRequestType){
//
//    }
    @PostMapping("singIn")
    public String singIn(@RequestParam("login") String login, @RequestParam("password")String password){
        return roleService.singIn(login,password);
    }
    @PostMapping("singOut")
    public void singOut(@RequestParam("login") String login){
        roleService.signOut(login);
    }
}
