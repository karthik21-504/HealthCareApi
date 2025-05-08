package com.example.project.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;

@RestController
public class ApplicationUserController {
   @Autowired
    private ApplicationUserService applicationUserService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody ApplicationUser user) {
        return applicationUserService.loginUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ApplicationUser user) {
        return applicationUserService.registerUser(user);
    }

    @GetMapping("/viewprofile/{userId}")
    public ResponseEntity<JSONObject> viewProfile(@PathVariable String userId) {
        return applicationUserService.viewProfile(userId);
    }

    @PostMapping("/editprofile/{userId}")
    public ResponseEntity<JSONObject> editProfile(@PathVariable String userId, @RequestBody ApplicationUser updatedUser) {
        return applicationUserService.editProfile(userId, updatedUser);
    }


}
