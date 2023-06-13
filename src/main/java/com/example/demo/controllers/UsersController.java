package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    Integer uidInt = Integer.parseInt(uid);
    // Call the data access layer to find the user by uid
    User user = userRepo.findByUid(uidInt).get(0);

    model.addAttribute("user", user);

    return "users/editUser";
    }

    @PostMapping("/users/edit/{uid}")
    @Transactional
    public String editUser(Model model, @PathVariable String uid, @RequestParam Map<String, String> editeduser, HttpServletResponse response) {
        System.out.println("EDIT User " + uid);
        Integer uidInt = Integer.parseInt(uid);

        // Call the data access layer to find the user by uid
        User user = userRepo.findByUid(uidInt).get(0);
        user.setName(editeduser.get("name"));
        user.setHeight(Integer.parseInt(editeduser.get("height")));
        user.setWeight(Integer.parseInt(editeduser.get("weight")));
        user.setColor(editeduser.get("color"));
        user.setGpa(Double.parseDouble(editeduser.get("gpa")));

        return "users/editedUser";
    }
    @PostMapping("/users/delete/{uid}")
    @Transactional
    public String deleteUser(Model model, @PathVariable String uid, @RequestParam Map<String, String> editeduser, HttpServletResponse response) {
        System.out.println("DELETE User " + uid);
        Integer uidInt = Integer.parseInt(uid);

        // Call the data access layer to find the user by uid
        User user = userRepo.findByUid(uidInt).get(0);
        userRepo.delete(user);
        return "users/deletedUser";
    }

    // data coming from form would be a PostMapping
    @PostMapping(value="/users/add")
    public String AddUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        // get name, size, password from form ...
        System.out.println("ADD user");
        String newName = newuser.get("name");
        int newHeight = Integer.parseInt(newuser.get("height"));
        int newWeight = Integer.parseInt(newuser.get("weight"));
        String newColor = newuser.get("color");
        double newGpa = Double.parseDouble(newuser.get("gpa"));
        userRepo.save(new User(newName, newHeight, newWeight, newColor, newGpa));
        response.setStatus(201);
        return "users/addedUser";
    }
    
}
