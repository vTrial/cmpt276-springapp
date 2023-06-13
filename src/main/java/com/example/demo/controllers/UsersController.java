package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;



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
    @PostMapping(value="/users/add")
    public String AddUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        // get name, size, password from form ...
        System.out.println("ADD user");
        String newName = newuser.get("name");
        String newPassword = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));
        userRepo.save(new User(newName, newPassword, newSize));
        response.setStatus(201);
        return "users/addedUser";
    }
    
}
