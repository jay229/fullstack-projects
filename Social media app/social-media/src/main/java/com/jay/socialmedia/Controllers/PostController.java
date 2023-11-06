package com.jay.socialmedia.Controllers;
import com.jay.socialmedia.Models.Post;
import com.jay.socialmedia.Service.PostService;
import com.jay.socialmedia.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable int userId) throws Exception {
        Post newPost=postService.createPost(post,userId);
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable int userId) throws Exception {
        String message= postService.deletePost(postId,userId);
        ApiResponse response=new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }

    @GetMapping("/find/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.FOUND);
    }
    @GetMapping("/user-posts/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable int userId){
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.FOUND);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Post>> findAllPosts(){
        List<Post> posts=postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.FOUND);
    }
    @PutMapping("/saved-post/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable int userId){
        Post post=postService.savedPost(postId,userId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable int userId) throws Exception {
        Post post=postService.likePost(postId,userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
