package com.example.blog.controller;

import com.example.blog.dto.BlogRequest;
import com.example.blog.dto.BlogResponse;
import com.example.blog.model.BlogPost;
import com.example.blog.model.BlogStatus;
import com.example.blog.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;




//http://localhost:8081/api/blog/

@RestController
@RequestMapping("/api/blog")

public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlog(@RequestBody BlogRequest blogRequest, Authentication authentication){

        String username = authentication.getName();


        BlogPost blogPost = blogService.createBlog(blogRequest, username);

        return ResponseEntity.status(HttpStatus.CREATED).body(blogPost);


}
    @GetMapping("/page")
    public ResponseEntity<Page<BlogResponse>> getAllBlogs( @RequestParam (defaultValue = "0") int page,
                                                           @RequestParam (defaultValue = "10") int size){
        Page <BlogResponse> blogs = blogService.getAllBlogsWithPagiationint(page, size);
        return ResponseEntity.ok(blogs);

            }

    @PutMapping("/{blogID}")
            public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long blogID, @RequestBody BlogRequest blogRequest, Authentication authentication){
        String username = authentication.getName();

        BlogPost updatedBlog = blogService.updateBlog(blogID, blogRequest, username);

        BlogResponse blogResponse= new BlogResponse();

        blogResponse.setBlogId(updatedBlog.getBlogID());
        blogResponse.setTitle(updatedBlog.getTitle());
        blogResponse.setContent(updatedBlog.getContent());
        blogResponse.setCategory(updatedBlog.getCategory());
        blogResponse.setStatus(updatedBlog.getStatus());

        return ResponseEntity.ok(blogResponse);

    }


    @GetMapping("/slug/{slug}")
    public ResponseEntity <BlogResponse> getBlogBySlug(@PathVariable String slug){

     return ResponseEntity.ok(blogService.getBlogBySlug(slug));
    }

    @DeleteMapping("/delete/blogid/{blogID}")
    public ResponseEntity <BlogPost> deleteBlogByID(@PathVariable Long blogID, Authentication authentication){
String username= authentication.getName();
    BlogPost deletedBlog = blogService.deleteBlogByID(blogID,username);


    return ResponseEntity.ok(deletedBlog);
    }


    @GetMapping("/id/{blogID}")
    public ResponseEntity <BlogResponse> getBlogByID(@PathVariable Long blogID){
        return ResponseEntity.ok(blogService.getBlogByID(blogID));
    }

    @DeleteMapping("/delete/slug/{slug}")
    public ResponseEntity <BlogPost> deleteBlogBySlug(@PathVariable String slug, Authentication authentication){

        String username = authentication.getName();
        BlogPost deletedBlog = blogService.deleteBlogBySlug(slug,username);

        return ResponseEntity.ok(deletedBlog);

    }
/*
    @GetMapping("/category-filter")
    public List<BlogResponse> getBlogsByCategory (@RequestParam (required = false)BlogStatus status){
        return blogService.getCategoryFilteredBooks(status);
    }
*/

    @GetMapping("/category-filter")
    public ResponseEntity <List <BlogResponse>> getBlogsByCategory(@RequestParam(required = false) String category){

        return ResponseEntity.ok(blogService.getCategoryFilteredBooks(category));
    }
    //http://localhost:8081/api/blog/status?status=DRAFT
    @GetMapping("/status")
    public ResponseEntity <List <BlogResponse>> getBlogsByStatus(@RequestParam (required = false) BlogStatus status, Authentication authentication)
    {
        String username = authentication.getName();


        return ResponseEntity.ok(blogService.getStatusFilteredBlogs(status, username));
    }




}
