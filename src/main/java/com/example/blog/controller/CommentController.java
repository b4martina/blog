package com.example.blog.controller;


import com.example.blog.dto.CommentRequest;
import com.example.blog.dto.CommentResponse;
import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{blogID}")
    public List<CommentResponse> getAllBlogComments(@PathVariable Long blogID){
        return commentService.getAllComments(blogID);
    }


   /* @DeleteMapping("/{{blogId}/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(
            @PathVariable Long blogId,
            @PathVariable Long commentId,
            Authentication authentication) {

       CommentResponse response = commentService.deleteByCommentId (blogId, commentId, authentication.getName());

        return ResponseEntity.ok(response);
    }*/

    @DeleteMapping("/{blogId}/{commentId}")
    public Comment deleteComment(@PathVariable Long blogId,
                                 @PathVariable Long commentId,
                                 Authentication authentication) {

        return commentService.deleteByCommentId(blogId, commentId, authentication.getName()
        );
    }



}
