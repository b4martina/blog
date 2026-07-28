package com.example.blog.repository;

import com.example.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogPostRepository extends JpaRepository <BlogPost, Long> {

    Optional<BlogPost> findBySlug(String slug);
    boolean existsBySlug(String slug);

    Optional<BlogPost> findByBlogID(Long blogID);
    boolean existsByBlogID(Long blogID);


}

