package com.thoughtworks.training.kmj.todoservice.service;

import com.thoughtworks.training.kmj.todoservice.model.ToDo;
import com.thoughtworks.training.kmj.todoservice.repository.ToDoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

     public List<ToDo> getList() {
        List<ToDo> list = toDoRepository.findAll();
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
