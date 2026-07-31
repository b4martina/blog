package com.example.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="blogs")
public class BlogPost {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="blogID")
    private Long blogID;

@Column(name="TITLE")
    private String title;

@Column(name = "CONTENT")
    private String content;

@Column(name="CATEGORY")
private String category;


@Enumerated(EnumType.STRING)
@Column(name="STATUS")
private BlogStatus status;

@Column(name="SLUG", unique = true, nullable = false)
private String slug;



    @ManyToOne
    @JoinColumn(name= "author_id")
    @JsonIgnore
    private User blogAuthor;

    @OneToMany(mappedBy="blogPost")
    @JsonIgnore
    private List<Comment> comment= new ArrayList<>();


    public BlogPost() {
    }

    public BlogPost(Long blogID, String title, String content, String category, BlogStatus status, String slug, User author) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.blogAuthor=author;
        this.slug=slug;
    }




}
