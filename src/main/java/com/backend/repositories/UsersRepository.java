package com.backend.repositories;

import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User s WHERE s.username=?1")
    Optional<User> findUserByUsername(String Username);

    @Query("SELECT u FROM User u WHERE u.password = ?1 AND u.username = ?2 AND u.type = ?3")
    Optional<User> authenticate(String password, String username, UserAccountType type);

}
