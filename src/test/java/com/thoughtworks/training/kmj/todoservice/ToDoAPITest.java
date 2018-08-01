package com.thoughtworks.training.kmj.todoservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.jayway.jsonpath.TypeRef;
import com.sun.tools.javac.comp.Todo;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import com.thoughtworks.training.kmj.todoservice.repository.ToDoRepository;
import com.thoughtworks.training.kmj.todoservice.service.ToDoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ToDoAPITest {

    //     返回list时        .andExpect(jsonPath("$[0].id").value(1))

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ToDoService toDoService;




    @Test
    public void shouldReturnItemLists() throws Exception {

        when(toDoService.getToDoList()).thenReturn(ImmutableList.of(new ToDo(0, "todox")));


        MvcResult result = mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].content").value("todox"))


                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus(),is(200));
    }

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
