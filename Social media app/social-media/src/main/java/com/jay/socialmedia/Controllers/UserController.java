package com.jay.socialmedia.Controllers;

import com.jay.socialmedia.Models.User;
import com.jay.socialmedia.Repository.UserRepository;
import com.jay.socialmedia.Service.UserService;
import com.jay.socialmedia.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("api/user/search-users")
    public List<User> getUsers(@RequestParam("query") String query){
      List<User>users=userService.searchUser(query);
      return users;
    }
    @GetMapping("api/user/get-all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("api/user/search/{id}")
    public User getUserById(@PathVariable("id") int id) throws Exception {
        User user=userService.findUserById(id);
        return user;
    }



    @PutMapping("user/update/{id}")
    public User updateUser(@RequestBody User user,@PathVariable  int id){
        return userService.updateUser(user,id);
    }
    @PutMapping("user/follow/{userId1}/{userId2}")
    public User followUser(@PathVariable int userId1, @PathVariable int userId2){
        return userService.followUser(userId1,userId2);
    }

    @DeleteMapping("user/delete/{id}")
    public String deleteUserById(@PathVariable int id){
        return "User is deleted with id"+id;
    }
}
