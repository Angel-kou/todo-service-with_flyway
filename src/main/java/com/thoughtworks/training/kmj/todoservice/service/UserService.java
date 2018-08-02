package com.thoughtworks.training.kmj.todoservice.service;

import com.thoughtworks.training.kmj.todoservice.model.User;
import com.thoughtworks.training.kmj.todoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public List<User> find() {
        return userRepository.findAll();
    }
}
