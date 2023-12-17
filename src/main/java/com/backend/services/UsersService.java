package com.backend.services;

import com.backend.controllers.UsersController;
import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import com.backend.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    public void FindUser(String password, String username,UserAccountType type ) {

        boolean exists =usersRepository.authenticate(password,username, type);
        if(!exists){
            throw new IllegalStateException("User does not exists");
        }
        //usersRepository.deleteById(userId);
        System.out.println("OK");
    }

}
