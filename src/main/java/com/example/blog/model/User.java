package com.example.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Users",
        uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME")
)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="ID")
    private Long id;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name="NAME")
    private String name;

    public User(){

    }

    public User(Long id, String email, String password, String username, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    @OneToMany(mappedBy="blogAuthor")
    @JsonIgnore
    private List<BlogPost> blogs = new ArrayList<>();



}
