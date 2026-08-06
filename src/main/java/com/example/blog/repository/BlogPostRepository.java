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

    //find blogs of username
    //@Query (nativeQuery = true, value= "SELECT * from blogs where ")

    @Query(value = "SELECT * FROM blogs WHERE author_id = :userId and status = :status", nativeQuery = true)
    List<BlogPost> findStatusBlogsByAuthorId(@Param("userId") Long userId,
                                             @Param("status") String status);

    //author id
    @Query (nativeQuery = true, value = "SELECT * from blogs where author_id= :userId  ")
    List <BlogPost> findBlogsByUserID (@Param ("userId") Long userId);


    @Query(value = "SELECT * FROM blogs WHERE author_id = :userId", nativeQuery = true)
    List<BlogPost> findBlogsByAuthorId(@Param("userId") Long userId);
    //ordered by id
    @Query (nativeQuery = true, value = "SELECT * FROM blogs ORDER BY  blogid")
    List <BlogPost> findBlogsIdOrdered();
    //get multiple filters
    @Query (nativeQuery = true, value= "select * from blogs WHERE status = 'PUBLISHED' and (:userId IS NULL OR author_id = :userId) AND (:category IS NULL OR category = :category) AND (:content IS NULL OR LOWER(content) LIKE LOWER(CONCAT('%', :content, '%')))")
    List <BlogPost> findBlogsFilteredTwo(@Param ("userId") Long userId,
                                         @Param("category") String category,
                                         @Param ("content" ) String content);


    List <BlogPost> findByTitleContainingIgnoreCase(String title);


    List <BlogPost> findByContentContainingIgnoreCase(String content);


}

