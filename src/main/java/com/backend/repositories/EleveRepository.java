package com.backend.repositories;

import com.backend.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EleveRepository extends JpaRepository<Eleve,Long> {




    @Modifying
    @Query("update Eleve e set e.position = ?1 WHERE e.id = ?2")
    void updateElevePos(String nouvellePosition, Long id);

}
