package com.rebrova.pizzaproject.service;

import com.rebrova.pizzaproject.dtos.UserDto;
import com.rebrova.pizzaproject.model.Role;
import com.rebrova.pizzaproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role updateRole(Integer id, Role update) {
        Role role = roleRepository.findById(id).get();
        if (Objects.nonNull(update.getRole()) && !"".equalsIgnoreCase(update.getRole())) {
            role.setRole(update.getRole());
        }
        if (Objects.nonNull(update.getLogin()) && !"".equalsIgnoreCase(update.getLogin())) {
            role.setLogin(update.getLogin());
        }
        if (Objects.nonNull(update.getPassword()) && !"".equalsIgnoreCase(update.getPassword())) {
            role.setPassword(update.getPassword());
        }
        if (Objects.nonNull(update.getUserId()) && (update.getUserId()) != 0) {
            role.setUserId(update.getUserId());
        }
        if (Objects.nonNull(update.getFlag()) ) {
            role.setFlag(update.getFlag());
        }
        roleRepository.save(role);
        return role;
    }

    @Override
    public String singIn(String login, String password) {
        Role role = roleRepository.findByLogin(login).get();
        System.out.println(role.getLogin());
        if(role==null) return null;
        if(!role.getPassword().equals(password)) return null;
        role.setFlag(1);
        roleRepository.save(role);
        return role.getRole();
    }

    @Override
    public void signOut(String login) {
        Role role = roleRepository.findByLogin(login).get();
        role.setFlag(0);
        roleRepository.save(role);
    }

    @Override
    public Integer getInto(String login) {
        Role role = roleRepository.findByLogin(login).get();
        return role.getFlag();
    }
}
