/*package com.example.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comments")



public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(name= "comment_body")
    private String commentBody;

    @ManyToOne
    @JoinColumn(name= "commentAuthor_id")
    @JsonIgnore
    private User commentAuthor;





}
*/