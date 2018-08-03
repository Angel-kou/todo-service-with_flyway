package com.thoughtworks.training.kmj.todoservice.controller;


import com.thoughtworks.training.kmj.todoservice.model.User;
import com.thoughtworks.training.kmj.todoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String create(@RequestBody User user)  {
        System.out.println("----111------");
        return userService.create(user);
    }

    @GetMapping("/users")
    public List<User> getList()  {
       return userService.find();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user)  {
        return userService.login(user);
    }


}
