package com.example.demo1.application;

import com.example.demo1.models.Role;
import com.example.demo1.models.User;
import com.example.demo1.repositories.RoleRepository;
import com.example.demo1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //communicating with JPA Directory och make querie for us

    @Override
    public User saveUser(User user) {
        log.info("Saving new user",user.getUsername());
       return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRole(String username, String roleName) {
        User user= userRepository.findByUsername(username);
        Role role= roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}


