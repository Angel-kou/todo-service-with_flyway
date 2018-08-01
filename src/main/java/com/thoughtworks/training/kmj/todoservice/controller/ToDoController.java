package com.thoughtworks.training.kmj.todoservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import com.thoughtworks.training.kmj.todoservice.service.HelloService;
import com.thoughtworks.training.kmj.todoservice.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ToDoController {
    @Autowired
    private ToDoService todoService;

    @Autowired
    private ObjectMapper objectMapper;


//    @RequestMapping(method = RequestMethod.GET,path="/todos")
//    public List<ToDo> toDoList() throws JsonProcessingException {
////        model.addAttribute("todoList",todoService.getList());
//        List<ToDo> list = todoService.getList();
//        System.out.println(objectMapper.writeValueAsString(list));
//        return list;
//    }


    @RequestMapping(method = RequestMethod.GET,path="/todos")
    public Map<String,List<ToDo>> toDoList() throws IOException {
        return todoService.getToDoList();
    }

}
