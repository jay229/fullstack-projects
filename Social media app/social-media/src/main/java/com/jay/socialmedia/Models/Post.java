package com.jay.socialmedia.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    private String image;
    private String video;
    @JsonIgnore
    @OneToMany
    private List<User> like=new ArrayList<>();
    @ManyToOne
    private User user;
    @CreationTimestamp
    private Date createdAt;
//    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(Integer id, String caption, String image, String video, List<User> like, User user) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.like = like;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getLike() {
        return like;
    }

    public void setLike(List<User> like) {
        this.like = like;
    }
}
