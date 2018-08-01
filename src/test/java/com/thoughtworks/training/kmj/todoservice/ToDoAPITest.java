package com.thoughtworks.training.kmj.todoservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.TypeRef;
import com.sun.tools.javac.comp.Todo;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ToDoAPITest {
    @Test
    public void jsonToObject() throws IOException {
        String json = "{\"id\":1,\"content\":\"todo1\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo todo = objectMapper.readValue(json,ToDo.class);
        System.out.println(todo);
    }


    @Test
    public void jsonToObjectArray() throws IOException {
        String json = "[{\"id\":1,\"content\":\"todo1\"},{\"id\":2,\"content\":\"todo2\"},{\"id\":3,\"content\":\"todo3\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<ToDo> list = objectMapper.readValue(json,
                new TypeReference<List<ToDo>>(){}
                );
        System.out.println(list);
    }
}
