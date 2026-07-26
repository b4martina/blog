package com.example.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

@Entity
@Data
@Table(name="Blogs")
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


    @ManyToOne
    @JoinColumn(name= "author_id")
    @JsonIgnore
    private User blogAuthor;

    public BlogPost() {
    }

    public BlogPost(Long blogID, String title, String content, String category, BlogStatus status, User author) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.blogAuthor=author;
    }




}
