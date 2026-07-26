package com.example.blog.dto;

import com.example.blog.model.BlogStatus;

public class BlogResponse {
    private Long blogId;
    public String title;
    public String content;
    public String category;
    public BlogStatus status;

    public BlogResponse(){}

    public BlogResponse(Long blogId, String title, String content, String category, BlogStatus status) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BlogStatus getStatus() {
        return status;
    }

    public void setStatus(BlogStatus status) {
        this.status = status;
    }
}
