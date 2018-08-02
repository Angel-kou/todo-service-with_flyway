package com.thoughtworks.training.kmj.todoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    @JsonIgnore
    private ToDo toDo;

}
