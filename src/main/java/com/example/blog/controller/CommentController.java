package com.example.blog.controller;


import com.example.blog.dto.CommentRequest;
import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/{blogID}")
    public Comment createComment(@PathVariable Long blogID, @RequestBody CommentRequest commentRequest, Authentication authentication){
       return commentService.createComment(commentRequest, blogID,authentication.getName());
    }



}
