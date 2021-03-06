package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.update.UpdateProfileModel;
import com.currency.convertor.repository.UserRepository;
import com.currency.convertor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePassword(@RequestBody UpdateProfileModel model, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(this.userService.updateUserProfile(model, user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/profile")
    public User getUserProfile(@AuthenticationPrincipal User user) {
        return this.userService.getUserProfile(user);
    }
}
