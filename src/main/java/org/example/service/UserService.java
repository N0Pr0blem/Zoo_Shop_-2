package org.example.service;

import org.example.model.Cheque;
import org.example.model.Role;
import org.example.model.User;
import org.example.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public void add(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB == null) {
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }
    }

    public User getUser(String name) {
        return userRepository.findByUsername(name);
    }

    public void makeAdmin(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(!userFromDb.getRoles().contains(Role.ADMIN)){
            userRepository.save(userFromDb.addRole(Role.ADMIN));
        }
    }

    public void makeUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb.getRoles().contains(Role.ADMIN)){
            userFromDb.getRoles().clear();
            userFromDb.getRoles().add(Role.USER);
            userRepository.save(userFromDb);
        }
    }
}
