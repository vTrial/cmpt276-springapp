package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;



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

    @GetMapping("/users/view/{uid}")
    public String getUser(Model model, @PathVariable String uid){
        
        System.out.println("GET User " + uid);
        // call db
   
        model.addAttribute("user");
        return "showUser";
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
