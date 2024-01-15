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


    public List<User> GetUsers(){
        return usersRepository.findAll();

    }
    public void addNewUser(User user){
        Optional<User> studentOptional=usersRepository
                .findUserByUsername(user.getUsername());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Username taken");
        }
        usersRepository.save(user);

    }

    public void deleteUser(Long userId) {
        boolean exists =usersRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id "+userId + "does not exists");
        }
        usersRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId, String username, String password) {
        User user = usersRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("user with id "+ userId+" does not existe"));

        if(username !=null &&
                username.length()>0 &&
                !Objects.equals(user.getUsername(),username)){
            Optional<User> userOptional =usersRepository.findUserByUsername(username);
            if(userOptional.isPresent()){
                throw new IllegalStateException("username taken");
            }
            user.setUsername(username);
        }

        if(password !=null &&
                password.length()>0 &&
                !Objects.equals(user.getPassword(),password)){


            user.setPassword(password);
        }
    }
    @Transactional
    public void updateUserPassword(Long userId, String oldPassword, String newPassword) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));

        if (oldPassword != null &&
                oldPassword.length() > 0 &&
                !Objects.equals(oldPassword, user.getPassword())) {
            throw new IllegalStateException("Invalid old password");
        }

        if (newPassword != null &&
                newPassword.length() > 0 &&
                !Objects.equals(user.getPassword(), newPassword)) {
            user.setPassword(newPassword);
        }
    }

    public User SearchUser(String password, String username,UserAccountType type ) {

        Optional<User> userOptional =usersRepository.authenticate(password,username,type);
        if( userOptional.isPresent()){
            User user = userOptional.get();
            System.out.println("Authentification OK");
            return user;
            //
        }else{throw new IllegalStateException("User does not exists");}


    }
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

}