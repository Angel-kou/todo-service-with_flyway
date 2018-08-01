package com.thoughtworks.training.kmj.todoservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import com.thoughtworks.training.kmj.todoservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;



    public List<ToDo> getList() {
        List<ToDo> list = toDoRepository.findAll();
        return list;
    }


//    public List<ToDo> getToDoList() throws IOException {
//        String json = toDoRepository.getData();
//        ObjectMapper objectMapper = new ObjectMapper();
////        Map<String,List<ToDo>> map = objectMapper.readValue(json,
////                new TypeReference<Map<String,List<ToDo>>>(){}
////        );
//         List<ToDo> map = objectMapper.readValue(json,
//                new TypeReference<List<ToDo>>(){}
//        );
////        System.out.println(map);
//        return map;
//    }
}
