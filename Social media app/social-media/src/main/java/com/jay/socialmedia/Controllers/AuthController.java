package com.jay.socialmedia.Controllers;

import com.jay.socialmedia.Models.User;
import com.jay.socialmedia.Repository.UserRepository;
import com.jay.socialmedia.Service.CustomUserDetailService;
import com.jay.socialmedia.Service.UserService;
import com.jay.socialmedia.config.JwtProvider;
import com.jay.socialmedia.request.LoginRequest;
import com.jay.socialmedia.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist=userRepository.findByEmail(user.getEmail());
        if (isExist!=null)
            throw new Exception("email already exists with another account");
        User user1=new User();
        user1.setId(user.getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=userRepository.save(user1);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token= JwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(token,"Register success");
        return authResponse;
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(token,"Login success");
        return authResponse;

    }
    public Authentication authenticate(String email, String password){
        UserDetails userDetails=customUserDetailService.loadUserByUsername(email);
        if (userDetails==null)
            throw new BadCredentialsException("Invalid username");
        if (!passwordEncoder.matches(password,userDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
