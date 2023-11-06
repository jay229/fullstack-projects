package com.jay.socialmedia.Service;

import com.jay.socialmedia.Models.Post;
import com.jay.socialmedia.Models.User;
import com.jay.socialmedia.Repository.PostRepository;
import com.jay.socialmedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
   UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Override
    public Post createPost(Post post, int userId) throws Exception {
       User user=userService.findUserById(userId);
       Post newPost=new Post();
       newPost.setCaption(post.getCaption());
       newPost.setImage(post.getImage());
       newPost.setVideo(post.getVideo());
       newPost.setUser(user);
       return postRepository.save(newPost);

    }

    @Override
    public String deletePost(Integer postId, int userId) throws Exception {
        User user=userService.findUserById(userId);
//        Optional<Post> post=postRepository.findById(postId);
        Optional<Post> post=postRepository.findById(postId);
        if (post.isEmpty())
            throw new Exception("Post does not exists.");
        if(post.get().getUser().getId()!=userId)
            throw new Exception("You can't delete another user's post");
        postRepository.delete(post.get());
        return "Post deleted successfully...";

    }

    @Override
    public List<Post> findPostByUserId(int userId) {
        List<Post>posts=postRepository.findPostByUserId(userId);
        return posts;
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> optionalPost=postRepository.findById(postId);
        if (optionalPost.isEmpty())
                throw new Exception("Post not found");
        return optionalPost.get();
    }

    @Override
    public List<Post> findAllPosts() {
        List<Post>posts=postRepository.findAll();
        return posts;
    }

    @Override
    public Post savedPost(Integer postId, int userId) {
        User user=userRepository.findById(userId).get();
        Post post=postRepository.findById(postId).get();
        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else
            user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, int userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post=findPostById(postId);
        if (post.getLike().contains(user)){
            post.getLike().remove(user);
        }
        else
            post.getLike().add(user);
        return postRepository.save(post);
    }

}
