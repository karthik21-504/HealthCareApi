package com.example.project.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.project.Model.ApplicationUser;
import com.example.project.config.JwtUtil;
import com.example.project.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {
  @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<JSONObject> registerUser(@RequestBody ApplicationUser user) {
        JSONObject response = new JSONObject();
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
            response.put("status", "success");
            response.put("message", "Registration successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Password or username policy failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<JSONObject> loginUser(@RequestBody ApplicationUser loginRequest) {
        JSONObject response = new JSONObject();
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUser_name(),
                    loginRequest.getPassword()
                )
            );

            ApplicationUser user = applicationUserRepository.findByUser_name(loginRequest.getUser_name()).get();
            String token = jwtUtil.generateToken(user);

            response.put("status", "success");
            response.put("token", token);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

     public ResponseEntity<JSONObject> viewProfile(String userId) {
        ApplicationUser users = applicationUserRepository.findById(userId).get();
        JSONObject response = new JSONObject();
        response.put("users", users);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<JSONObject> editProfile(String userId, @RequestBody ApplicationUser updatedUser) {
        Optional<ApplicationUser> optionalUser = applicationUserRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            ApplicationUser user = optionalUser.get();
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getMobile_number() != null) {
                user.setMobile_number(updatedUser.getMobile_number());
            }
            if (updatedUser.getAddress() != null) {
                user.setAddress(updatedUser.getAddress());
            }
            applicationUserRepository.save(user);
            JSONObject response = new JSONObject();
            response.put("message", "Profile updated successfully");
            response.put("user", user);

            return ResponseEntity.ok(response);
        } else {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "User not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}