package com.backend.controllers;

import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import com.backend.repositories.UsersRepository;
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
   // private final UsersRepository usersRepository;

    //@Autowired
    //public UsersController(UsersRepository usersRepository) { this.usersRepository = usersRepository;    }

    //@GetMapping
  //  public ResponseEntity<Object> index() {    return ResponseEntity.ok(usersRepository.findAll());     }


    private final UsersService userService;
    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }



    @PostMapping("/Auth")
    public ResponseEntity<String> findUser(@RequestParam String password, @RequestParam String username,@RequestParam UserAccountType type) {
        try {

            userService.FindUser(password, username,type);
            return ResponseEntity.ok("Authentication successful");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Authentication failed");
        }
    }

}
