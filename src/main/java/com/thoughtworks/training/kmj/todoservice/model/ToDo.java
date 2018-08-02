package com.thoughtworks.training.kmj.todoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Todo")
@SQLDelete(sql="update todo set deleted=true where id = ?")
@Where(clause = "deleted = false")
public class ToDo {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    private boolean completed;

    private boolean readonly;

//    @OneToMany(targetEntity = Task.class , cascade = CascadeType.ALL,mappedBy = "toDo") // 关系被维护端
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_id")
    private List<Task> tasks;


}
