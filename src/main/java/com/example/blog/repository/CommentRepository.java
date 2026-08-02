package com.example.blog.repository;

import com.example.blog.dto.CommentResponse;
import com.example.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogPost_BlogID(Long blogID);

    Optional<Comment> findByCommentId(Long commentId);








}
