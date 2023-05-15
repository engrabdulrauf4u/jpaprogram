package com.test.jpaprogram.controller;

import com.test.jpaprogram.model.User;
import com.test.jpaprogram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping(value = "/home")
    public String getWelcome(){
        return "Welcome ";
    }


    @GetMapping(value = "/home/users")  // allow publically
    public List<User> getUsers(){
        return userRepository.findAll();
    }


    @GetMapping(value = "/home/normalUser")
    public String getNormalUsers(){
        return "This is normal User";
    }

    @GetMapping(value = "/home/adminUser")
    public String getAdminUser(){
        return "This is Admin User";
    }



    @PostMapping(value = "/home/save")  // allow to save by adimin usre.
    public void saveUser(){
        User user = new User("Mehmood","Ullah", 33, "Administration","email@gmail.com");
        userRepository.save(user);
    }
}
