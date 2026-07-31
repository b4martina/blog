package com.example.blog.dto;

import com.example.blog.model.User;

import java.time.LocalDate;

public class CommentRequest {

    private String commentBody;


public CommentRequest(){};

    public CommentRequest( String commentBody) {

        this.commentBody = commentBody;

    }



    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }




    }

