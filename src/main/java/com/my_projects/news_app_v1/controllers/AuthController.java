package com.my_projects.news_app_v1.controllers;

import com.my_projects.news_app_v1.entity.User;
import com.my_projects.news_app_v1.models.LoginCredentials;
import com.my_projects.news_app_v1.repository.UserRepo;
import com.my_projects.news_app_v1.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepo userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user){

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return Collections.singletonMap("message", "User already exists");
        }
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("jwt-token", token);
        response.put("username", user.getUsername());

        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());
            User user = userRepo.findByEmail(body.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Map<String, Object> response = new HashMap<>();
            response.put("jwt-token", token);
            response.put("username", user.getUsername());

            return response;
        } catch (UsernameNotFoundException e) {
            return Collections.singletonMap("message", "User not found");
        } catch (AuthenticationException authExc) {
            return Collections.singletonMap("message", "Invalid Login Credentials");
        }
    }
}