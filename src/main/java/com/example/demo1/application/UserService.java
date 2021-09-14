package com.example.demo1.application;

import com.example.demo1.models.Role;
import com.example.demo1.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRole(String username, String roleName);// duplicate username?? we don't allow, find out role in db
    User getUser(String username);//id?
    List<User> getUsers();
}
