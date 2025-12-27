package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public Comment() {}

    public Comment(String content){
        this.content=content;
    }

    public String getContent(){
        return content;
    }

    public void setComment(String content){
        this.content=content;
    }
}

