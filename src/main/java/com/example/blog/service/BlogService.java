package com.example.blog.service;

import com.example.blog.dto.BlogRequest;
import com.example.blog.dto.BlogResponse;
import com.example.blog.model.BlogPost;
import com.example.blog.model.BlogStatus;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public  class BlogService {

    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;

    public BlogService(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository=userRepository;
    }



    //slug generation
    private String generateSlug(String title) {

        return title
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }
//create blog
    public BlogPost createBlog (BlogRequest blogRequest, String username){
   User author = userRepository.findByUsername(username).orElseThrow(() ->new RuntimeException("User not found!"));


   BlogPost blogPost = new BlogPost();

blogPost.setTitle(blogRequest.getTitle());
blogPost.setContent(blogRequest.getContent());
blogPost.setCategory(blogRequest.getCategory());
        String slug = generateSlug(blogRequest.getTitle());

        if (blogPostRepository.existsBySlug(slug)) {
            slug = slug + "-" + System.currentTimeMillis();
        }

blogPost.setSlug(slug);
blogPost.setStatus(blogRequest.getStatus());





blogPost.setBlogAuthor(author);


      //  String slug = generateSlug(blogRequest.getTitle());


        // avoid duplicate slugs
        if (blogPostRepository.existsBySlug(slug)) {
            slug = slug + "-" + System.currentTimeMillis();
        }
return blogPostRepository.save(blogPost);
    }

    //get all posts
    public Page<BlogResponse> getAllBlogsWithPagiationint ( int page, int size){
        Pageable pageable = PageRequest.of(page,size);

        Page<BlogPost> blogs= blogPostRepository.findAll(pageable);

        return blogs.map(blog -> new BlogResponse(
                blog.getBlogID(),
        blog.getTitle(), blog.getContent(), blog.getCategory(), blog.getSlug(), blog.getStatus()
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

        String slug = generateSlug(blogRequest.getTitle());

        if (!slug.equals(blogPost.getSlug())
                && blogPostRepository.existsBySlug(slug)) {

            slug = slug + "-" + System.currentTimeMillis();
        }

        blogPost.setSlug(slug);
        return blogPostRepository.save(blogPost);


    }


    public BlogResponse getBlogBySlug(String slug){

        BlogPost blogPost= blogPostRepository.findBySlug(slug).orElseThrow(() ->new RuntimeException("THe blog you searched for does not exist!"));

        BlogResponse blogResponse = new BlogResponse();

        //user=userRepository.findByUsername()


        blogResponse.setBlogId(blogPost.getBlogID());
        blogResponse.setTitle(blogPost.getTitle());
        blogResponse.setStatus(blogPost.getStatus());
        blogResponse.setContent(blogPost.getContent());
        blogResponse.setCategory(blogPost.getCategory());
        blogResponse.setStatus(blogPost.getStatus());
        blogResponse.setSlug(blogPost.getSlug());
        //Long blogID, String title, String content, String category, BlogStatus status, String slug, User author

     return  blogResponse  ;

    }

    public BlogResponse getBlogByID(Long blogID){
        BlogPost blogPost= blogPostRepository.findByBlogID(blogID)
                .orElseThrow(() -> new RuntimeException("No Blog Available by inserted blog ID"));
        BlogResponse blogResponse=new BlogResponse();


        blogResponse.setBlogId(blogPost.getBlogID());
        blogResponse.setTitle(blogPost.getTitle());
        blogResponse.setStatus(blogPost.getStatus());
        blogResponse.setContent(blogPost.getContent());
        blogResponse.setCategory(blogPost.getCategory());
        blogResponse.setStatus(blogPost.getStatus());
        blogResponse.setSlug(blogPost.getSlug());

        return blogResponse;

    }


    public BlogPost deleteBlogByID(Long blogID, String username ){

        BlogPost blogPost = blogPostRepository.findByBlogID(blogID)
                .orElseThrow(()-> new RuntimeException("Blog Not Found"));

        if(!blogPost.getBlogAuthor().getUsername().equals(username)){
            throw new RuntimeException("Blog can not be deleted!");
        }

         blogPostRepository.delete(blogPost);
         return blogPost;


    }

    public BlogPost deleteBlogBySlug(String slug, String username){

        BlogPost blogPost = blogPostRepository.findBySlug(slug).orElseThrow(()-> new RuntimeException("Blog Not Found!"));
        if(!blogPost.getBlogAuthor().getUsername().equals(username)){
            throw new RuntimeException("Blog can not be deleted!");
        }

        blogPostRepository.delete(blogPost);
        return blogPost;

    }


    //publication status: only an owner can view status : published or draft.
    public List<BlogResponse> getCategoryFilteredBooks (BlogStatus status){
        List <BlogPost> blogs;

        if (status== null) {
            blogs = blogPostRepository.findAll();
        }else{
            blogs = blogPostRepository.findFilteredBlogs(status.name());
        }
        List<BlogResponse> blogResponse= new ArrayList<>();

        for (BlogPost blog : blogs ){
            BlogResponse br = new BlogResponse();
            br.setTitle(blog.getTitle());
            br.setContent(blog.getContent());
            br.setCategory(blog.getCategory());
            br.setStatus(blog.getStatus());
            br.setSlug(blog.getSlug());

            blogResponse.add(br);

        }

        return blogResponse;

    }


    public List<BlogResponse> getCategoryFilteredBooks(String category){
        List <BlogPost> blogs;              // = blogPostRepository.findCategoryFilteredBlogs(category);

        if (category== null) {
            blogs = blogPostRepository.findAll();
        }else{
            blogs = blogPostRepository.findCategoryFilteredBlogs(category);
        }

     List <BlogResponse> blogResponsesList= new ArrayList<>();
        for (BlogPost blog : blogs) {

            BlogResponse blogResponse = new BlogResponse();

            blogResponse.setBlogId(blog.getBlogID());
            blogResponse.setTitle(blog.getTitle());
            blogResponse.setContent(blog.getContent());
            blogResponse.setCategory(blog.getCategory());
            blogResponse.setSlug(blog.getSlug());
            blogResponse.setStatus(blog.getStatus());

            blogResponsesList.add(blogResponse);

        }
        return blogResponsesList;



    }

    //filter by blog status (published or not)

    public List<BlogResponse> getStatusFilteredBlogs( BlogStatus status , String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        //List <BlogPost> blogs;              // = blogPostRepository.findCategoryFilteredBlogs(category);

        List<BlogPost> blogs = blogPostRepository.findStatusFilteredBlogs(
                user.getId(),
               status.name()
        );

        List <BlogResponse> blogResponses = new ArrayList<>();

        for (BlogPost blog : blogs){
            BlogResponse br = new BlogResponse();
            br.setBlogId(blog.getBlogID());
            br.setTitle(blog.getTitle());
            br.setContent(blog.getContent());
            br.setCategory(blog.getCategory());
            br.setSlug(blog.getSlug());
            br.setStatus(blog.getStatus());

            blogResponses.add(br);
        }

        return blogResponses;
    }

    public List<BlogResponse> getBlogsByTitle(String title){
        List <BlogPost> blogs;
        blogs = blogPostRepository.findByTitleContainingIgnoreCase(title);
        List <BlogResponse> blogResponses = new ArrayList<>();

        for (BlogPost blog : blogs) {
            BlogResponse br = new BlogResponse();
            br.setBlogId(blog.getBlogID());
            br.setTitle(blog.getTitle());
            br.setContent(blog.getContent());
            br.setCategory(blog.getCategory());
            br.setStatus(blog.getStatus());
            br.setSlug(blog.getSlug());

            blogResponses.add(br);
        }


        return blogResponses;
    }


    public List<BlogResponse> getBlogsByContent(String content){
       List <BlogPost> blogs;
        blogs=blogPostRepository.findByContentContainingIgnoreCase(content);

        List <BlogResponse> blogResponses = new ArrayList<>();

        for (BlogPost blog : blogs ){

            BlogResponse br = new BlogResponse();
            br.setBlogId(blog.getBlogID());
            br.setTitle(blog.getTitle());
            br.setContent(blog.getContent());
            br.setCategory(blog.getCategory());
            br.setStatus(blog.getStatus());
            br.setSlug(blog.getSlug());
            blogResponses.add(br);
        }
        return blogResponses;


    }
    





}
