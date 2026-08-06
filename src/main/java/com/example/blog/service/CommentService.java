package com.example.blog.service;


import com.example.blog.dto.CommentRequest;
import com.example.blog.dto.CommentResponse;
import com.example.blog.model.BlogPost;
import com.example.blog.model.BlogStatus;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        //if (blogPost.getStatus().equals("PUBLISHED") ) {throw new RuntimeException("Blog is not available");}
        if (blogPost.getStatus() != BlogStatus.PUBLISHED){throw new RuntimeException("Blog is not available");}

        Comment comment=new Comment();
        comment.setCommentBody(commentRequest.getCommentBody());
        comment.setCreatedAt(LocalDate.now());
        comment.setCommentAuthor(commentAuthor);
        comment.setBlogPost(blogPost);

        return commentRepository.save(comment);


    }

   public List<CommentResponse> getAllComments(Long blogId){

       List <Comment> comments = commentRepository.findByBlogPost_BlogID(blogId);

       List <CommentResponse> commentResponses=new ArrayList<>();
       for (Comment comment: comments){
          CommentResponse cr =new CommentResponse();
          cr.setCommentId(comment.getCommentId());
          cr.setCommentAuthor(comment.getCommentAuthor());
          cr.setCommentBody(comment.getCommentBody());
          cr.setCreatedAt(comment.getCreatedAt());
          cr.setBlogPost(comment.getBlogPost());

           commentResponses.add(cr);

       }


return commentResponses;


    }

     public Comment deleteByCommentId(Long blogId, Long commentId, String username){
         User commentAuthor = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
         BlogPost blogPost = blogPostRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog post not found!"));
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(()->new RuntimeException("Comment not found!"));
         if (!comment.getBlogPost().getBlogID().equals(blogId)) {
             throw new RuntimeException("Comment does not belong to this blog!");}
         if (!comment.getCommentAuthor().getId().equals(commentAuthor.getId())) {
             throw new RuntimeException("You can only delete your own comments!");}
         commentRepository.delete(comment);
         return comment;
     }

    public Comment deleteByPostSlug(String slug, Long commentId, String username){
       User commentAuthor = userRepository.findByUsername(username) .orElseThrow(()-> new RuntimeException("user nor found!"));
       BlogPost blogPost= blogPostRepository.findBySlug(slug).orElseThrow(()-> new RuntimeException("Blog not found :/"));
       Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(()-> new RuntimeException("comment does not exist"));
   if (!comment.getBlogPost().getSlug().equals(slug)){
       throw new RuntimeException("Comment does not belong to this blog!");}
    if (!comment.getCommentAuthor().getId().equals(commentAuthor.getId())){
        throw new RuntimeException("You can only delete your own comments!");}
    commentRepository.delete(comment);

    return comment;
   }




}
