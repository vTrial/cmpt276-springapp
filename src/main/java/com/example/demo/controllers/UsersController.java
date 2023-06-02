package com.example.demo.controllers;

import java.util.List;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {
    
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model){
        System.out.println("getting users");
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users/showAll";
    }

    // data coming from form would be a PostMapping
    @GetMapping(value="/users/add")
    public String AddUsers() {
        // get name, size, password from form ...
        User newuser = new User("henry","1234",25);
        userRepo.save(newuser);
        return "users/added";
    }
    
}
