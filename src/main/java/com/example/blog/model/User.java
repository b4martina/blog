package com.example.blog.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="ID")
    private Long id;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="USERNAME")
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
}
