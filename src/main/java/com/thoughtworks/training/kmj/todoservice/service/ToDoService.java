package com.thoughtworks.training.kmj.todoservice.service;

import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import com.thoughtworks.training.kmj.todoservice.repository.ToDoRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

     public List<ToDo> getList() {
         Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
         Object principal = authentication.getPrincipal();
         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         System.out.println("principal {}, "+principal+"---------role {}-------"+authorities);
//         int id  = userService.findIdByName((String) principal);
         List<ToDo> list = toDoRepository.findAllByUserIdIs((Integer) principal);
        return list;
    }

    public void create(ToDo todo) {
//        todo.getTasks().forEach(task -> task.setToDo(todo));

         toDoRepository.save(todo);
    }

    public void delete(Integer id) {
         toDoRepository.delete(id);
    }

    public ToDo update(Integer id) {
        ToDo toDo = toDoRepository.findOne(id);
        toDo.setContent("todoy");
        toDoRepository.save(toDo);
        return toDo;
    }

    public List<ToDo> getToDos(boolean completed) {
         return toDoRepository.findAllByCompletedIs(completed);
    }

    @Transactional
    public ToDo find(Integer id) throws NotFoundException {
         return
                 Optional.ofNullable(toDoRepository.findOne(id))
                         .orElseThrow(()->new NotFoundException("not found"));
    }
}
