package com.thoughtworks.training.kmj.todoservice.repository;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Repository
public class ToDoRepository {

    @Value(value="classpath:data.json")
    private Resource data;


    public List<ToDo> findAll() {
        return ImmutableList.of(
                new ToDo(1, "todo1"),
                new ToDo(2, "todo2"),
                new ToDo(3, "todo3")
        );
    }


    public String getData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(data.getInputStream()));
        StringBuffer message=new StringBuffer();
        String line = null;
        while((line = br.readLine()) != null) {
            message.append(line);
        }
        String defaultString=message.toString();
        String result=defaultString.replace("\r\n", "").replaceAll(" +", "");
        System.out.println(result);
        return result;
    }
}
