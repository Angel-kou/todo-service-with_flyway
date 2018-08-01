package com.thoughtworks.training.kmj.todoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ToDo {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    private boolean completed;

    private boolean readonly;


//    @JsonProperty("completed")
//    public boolean completed(){
//        return false;
//    }
//
//    @JsonProperty("readOnly")
//    public boolean readOnly(){
//        return true;
//    }
//
//
//    @JsonIgnore
//    @JsonProperty("test")
//    public String name(){
//        return "haha";
//    }
}
