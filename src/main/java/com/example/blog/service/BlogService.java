package com.example.blog.service;

import com.example.blog.dto.BlogRequest;
import com.example.blog.dto.BlogResponse;
import com.example.blog.model.BlogPost;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public  class BlogService {

    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;

    public BlogService(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository=userRepository;
    }

//create blog
    public BlogPost createBlog (BlogRequest blogRequest, String username){
   User author = userRepository.findByUsername(username).orElseThrow(() ->new RuntimeException("User not found!"));


   BlogPost blogPost = new BlogPost();

blogPost.setTitle(blogRequest.getTitle());
blogPost.setContent(blogRequest.getContent());
blogPost.setCategory(blogRequest.getCategory());
blogPost.setStatus(blogRequest.getStatus());
blogPost.setBlogAuthor(author);

return blogPostRepository.save(blogPost);
    }

    //get all posts
    public Page<BlogResponse> getAllBlogsWithPagiationint ( int page, int size){
        Pageable pageable = PageRequest.of(page,size);

        Page<BlogPost> blogs= blogPostRepository.findAll(pageable);

        return blogs.map(blog -> new BlogResponse(
                blog.getBlogID(),
        blog.getTitle(), blog.getContent(), blog.getCategory(), blog.getStatus()
        ));

    }

    //update blog
    public BlogPost updateBlog(Long blogID, BlogRequest blogRequest, String username){
        BlogPost blogPost = blogPostRepository.findById(blogID)
                .orElseThrow(()-> new RuntimeException("Blog Not Found"));

        if(!blogPost.getBlogAuthor().getUsername().equals(username)){
            throw new RuntimeException("Blog can not be updated!");

        }

    blogPost.setTitle(blogRequest.getTitle());
        blogPost.setContent(blogRequest.getContent());
        blogPost.setCategory(blogRequest.getCategory());

        blogPost.setStatus(blogRequest.getStatus());

        return blogPostRepository.save(blogPost);


    }

}
