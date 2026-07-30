package com.example.blog.repository;

import com.example.blog.model.BlogPost;
import com.example.blog.model.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository <BlogPost, Long> {

    Optional<BlogPost> findBySlug(String slug);
    boolean existsBySlug(String slug);

    Optional<BlogPost> findByBlogID(Long blogID);
    boolean existsByBlogID(Long blogID);

@Query(nativeQuery = true, value = "SELECT * from blogs where status= :status")
List<BlogPost> findFilteredBlogs (@Param("status") String status );

  /*  @Query(nativeQuery = true, value = "SELECT * from blogs where category= :category")
    List<BlogPost> findCategoryFilteredBlogs (@Param("category") String category );
*/


    @Query(nativeQuery=true, value="SELECT * FROM blogs where category = :category")
    List<BlogPost> findCategoryFilteredBlogs(@Param("category") String category);


    @Query (nativeQuery = true, value= "SELECT * from blogs where author_id = :userId and status = :status" )
    List<BlogPost> findStatusFilteredBlogs (    @Param("userId") Long id,
                                                @Param("status")String status);

    List <BlogPost> findByTitleContainingIgnoreCase(String title);
    List <BlogPost> findByContentContainingIgnoreCase(String content);


}

