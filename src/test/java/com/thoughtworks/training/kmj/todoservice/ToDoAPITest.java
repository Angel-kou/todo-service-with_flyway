package com.thoughtworks.training.kmj.todoservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ToDoAPITest {

    @Autowired
    private MockMvc mockMvc;


//    @MockBean
//    private ToDoService toDoService;



    @Test
    public void shouldReturnItemLists() throws Exception {

//        when(toDoService.getToDoList()).thenReturn(ImmutableList.of(new ToDo(0, "todox")));

//
//        MvcResult result = mockMvc.perform(get("/todos"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$[0].id").value(0))
//                .andExpect(jsonPath("$[0].content").value("todox"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//        System.out.println(result.getResponse().getStatus());
//        assertThat(result.getResponse().getStatus(),is(200));
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
