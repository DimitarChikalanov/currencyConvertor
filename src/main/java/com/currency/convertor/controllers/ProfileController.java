package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.update.UpdateEmailModel;
import com.currency.convertor.domain.model.update.UpdatePasswordModel;
import com.currency.convertor.domain.model.update.UpdateUsernameModel;
import com.currency.convertor.security.payload.response.MessageResponse;
import com.currency.convertor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordModel model, @AuthenticationPrincipal User user) {
        this.userService.updatePassword(model, user);
        return ResponseEntity.ok(new MessageResponse("Successful change password"));
    }

    @PostMapping("/username")
    public ResponseEntity<?> updateUsername(@RequestBody UpdateUsernameModel model, @AuthenticationPrincipal User user) {
        this.userService.updateUsername(model, user);
        return ResponseEntity.ok(new MessageResponse("Successful change username"));
    }

    @PostMapping("/email")
    public ResponseEntity<?> updateEmail(@RequestBody UpdateEmailModel model, @AuthenticationPrincipal User user) {
        this.userService.updateEmail(model, user);
        return ResponseEntity.ok(new MessageResponse("Successful change Email"));
    }
}
