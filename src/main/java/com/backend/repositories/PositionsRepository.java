package com.backend.repositories;

import com.backend.entities.Eleve;
import com.backend.entities.Positions;
import com.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PositionsRepository extends JpaRepository<Positions, Long> {

    @Query("SELECT p FROM Positions p WHERE p.username=?1")
    Optional<List<Positions>> findTrajetByUsername(String Username);


    @Query("SELECT p FROM Positions p WHERE p.username = ?1 ORDER BY p.createdAt DESC LIMIT 1")
    Optional<Positions> findLastPosByUsername(String username);



}
