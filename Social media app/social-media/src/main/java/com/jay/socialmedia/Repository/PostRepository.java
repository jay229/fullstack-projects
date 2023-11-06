package com.jay.socialmedia.Repository;

import com.jay.socialmedia.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.user.id=:userId")
    public List<Post> findPostByUserId(int userId);
}
