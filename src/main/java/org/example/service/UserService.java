package org.example.service;

import org.example.model.Address;
import org.example.model.Cheque;
import org.example.model.Role;
import org.example.model.User;
import org.example.repos.AddressRepository;
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
    @Autowired
    private AddressRepository addressRepository;

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

    public void addAddress(Principal principal, String city, String street, String corps, String houseNumber, String flatNumber) {
        User user = userRepository.findByUsername(principal.getName());
        Address address = new Address(city,street,corps, Integer.parseInt(houseNumber),Integer.parseInt(flatNumber));
        addressRepository.save(address);
        user.addAddress(address);
        userRepository.save(user);
    }

    public void deleteAddress(Principal principal, Address address) {
        User user = userRepository.findByUsername(principal.getName());
        user.getAddresses().remove(address);
        userRepository.save(user);
        addressRepository.delete(address);
    }

    public void changePassword(Principal principal, String oldPassword, String newPassword, String repeatNewPassword) {
        User user = userRepository.findByUsername(principal.getName());
        if(repeatNewPassword.equals(newPassword) && user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
