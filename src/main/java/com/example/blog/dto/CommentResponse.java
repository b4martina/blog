package com.example.blog.dto;

import com.example.blog.model.BlogPost;
import com.example.blog.model.User;

import java.time.LocalDate;

public class CommentResponse {

    private Long commentId;
    private String commentBody;
    private LocalDate createdAt;
    private User commentAuthor;
    private BlogPost blogPost;


    public CommentResponse(Long commentId, String commentBody, LocalDate createdAt, User commentAuthor, BlogPost blogPost) {
        this.commentId = commentId;
        this.commentBody = commentBody;
        this.createdAt = createdAt;
        this.commentAuthor = commentAuthor;
        this.blogPost = blogPost;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(User commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }
}
