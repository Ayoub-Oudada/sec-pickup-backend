package com.backend.repositories;

import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {


    @Query("SELECT COUNT(u) = 1 FROM User u WHERE u.password = ?1 AND u.username = ?2 AND u.type = ?3")
    boolean authenticate(String password, String username, UserAccountType type);

}
