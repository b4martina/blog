package com.example.blog.service;


import com.example.blog.dto.CommentRequest;
import com.example.blog.model.BlogPost;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {
    private CommentRepository commentRepository ;
    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;


    public CommentService(CommentRepository commentRepository, BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }


    public Comment createComment (CommentRequest commentRequest, Long blogId, String username) {
        User commentAuthor = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
        BlogPost blogPost = blogPostRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog post not found!"));

        Comment comment=new Comment();
        comment.setCommentBody(commentRequest.getCommentBody());
        comment.setCreatedAt(LocalDate.now());
        comment.setCommentAuthor(commentAuthor);
        comment.setBlogPost(blogPost);

        return commentRepository.save(comment);


    }

}
