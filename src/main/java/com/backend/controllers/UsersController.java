package com.backend.controllers;

import com.backend.entities.User;
import com.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService userService;

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

}
