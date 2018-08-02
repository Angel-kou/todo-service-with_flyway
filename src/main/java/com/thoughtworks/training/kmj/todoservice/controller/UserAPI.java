package com.thoughtworks.training.kmj.todoservice.controller;


import com.thoughtworks.training.kmj.todoservice.model.User;
import com.thoughtworks.training.kmj.todoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody User user)  {
        userService.create(user);
    }

    @GetMapping
    public List<User> getList()  {
       return userService.find();
    }

}
