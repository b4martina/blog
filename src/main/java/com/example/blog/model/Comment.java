package com.example.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Data
@Table(name="post_comments")

public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name="comment_id")
    private Long commentId;

    @Column(name= "comment_body")
    private String commentBody;

    @Column (name="created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name= "commentAuthor_id")
    @JsonIgnore
    private User commentAuthor;

    @ManyToOne
    @JoinColumn(name="blogPost_id")
    @JsonIgnore
    private BlogPost blogPost;


    public Comment() {
    }

    public Comment(Long commentId, String commentBody, LocalDate createdAt, User commentAuthor, BlogPost blogPost) {
        this.commentId = commentId;
        this.commentBody = commentBody;
        this.createdAt = createdAt;
        this.commentAuthor = commentAuthor;
        this.blogPost = blogPost;
    }
}
