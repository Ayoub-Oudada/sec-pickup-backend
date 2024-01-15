package com.backend.controllers;

import com.backend.entities.User;
import com.backend.services.MailService;
import com.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService userService;
    @Autowired
    private MailService mailService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> GetUsers() {
        return userService.GetUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long UserId) {
        userService.deleteUser(UserId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long UserId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        userService.updateUser(UserId, username, password);
    }
    @PutMapping("/{userId}/updatePassword")
    public ResponseEntity<String> updateUserPassword(
            @PathVariable("userId") Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        userService.updateUserPassword(userId, oldPassword, newPassword);

        return ResponseEntity.ok("Password updated successfully");
    }
    @PostMapping("/Auth")

    public User findUser(@RequestBody User user) {
        try {
            userService.SearchUser(user.getPassword(), user.getUsername(), user.getType());
            // userService.FindUser(password, username,type);
            ResponseEntity.ok("Authentication successful");
            return user;
        } catch (IllegalStateException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Authentication failed");
        }
        return null;
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("username") String username) {

        User user = userService.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Retrieve the user's email
        String userEmail = user.getEmail();

        // Send the existing password to the user's email
        String subject = "Password Retrieval";
        String body = "Your password is: " + user.getPassword();
        mailService.sendMail(userEmail, subject, body);

        return ResponseEntity.ok("Password sent to the registered email address.");
    }


}
