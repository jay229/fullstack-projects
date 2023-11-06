package com.jay.socialmedia.Service;

import com.jay.socialmedia.Models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(int userId) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User followUser(int userId1, int userId2);
    public User updateUser(User user,int id);
    public List<User> searchUser(String query);
    public List<User> getAllUsers();
}
