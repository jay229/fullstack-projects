package com.jay.socialmedia.Service;

import com.jay.socialmedia.Models.User;
import com.jay.socialmedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User user1=new User();
        user1.setId(user.getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        User savedUser=userRepository.save(user1);
        return savedUser;
    }

    @Override
    public User findUserById(int userId) throws Exception {
        Optional<User> user=userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        throw new Exception("User does not exists");
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if (user!=null){
            return user;
        }
        throw new Exception("User does not exists");
    }

    @Override
    public User followUser(int userId1, int userId2) {
       User user1=userRepository.findById(userId1).get();
       User user2=userRepository.findById(userId2).get();
       user2.getFollowers().add(user1.getId());
       user1.getFollowings().add(user2.getId());
       userRepository.save(user1);
       userRepository.save(user2);
       return user1;
    }

    @Override
    public User updateUser(User user,int id) {
        User user1=userRepository.findById(id).get();
        if (user.getId()!=0){
            user1.setId(user.getId());
        }
        if (user.getFirstName()!=null){
            user1.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!=null){
            user1.setLastName(user.getLastName());
        }
        if (user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }
        if (user.getPassword()!=null){
            user1.setPassword(user.getPassword());
        }
        if (user.getGender()!=null){
            user1.setGender(user.getGender());
        }
        User updatedUser=userRepository.save(user1);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        List<User>users=userRepository.searchUser(query);
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User>users=userRepository.findAll();
        return users;
    }
}
