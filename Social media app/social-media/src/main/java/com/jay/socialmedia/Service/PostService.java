package com.jay.socialmedia.Service;

import com.jay.socialmedia.Models.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post, int userId) throws Exception;
    public String deletePost(Integer postId,int userId) throws Exception;
    public List<Post> findPostByUserId(int userId);
    public Post findPostById(Integer postId) throws Exception;
    public List<Post> findAllPosts();
    public Post savedPost(Integer postId,int userId);
    public Post likePost(Integer postId,int userId) throws Exception;
}
