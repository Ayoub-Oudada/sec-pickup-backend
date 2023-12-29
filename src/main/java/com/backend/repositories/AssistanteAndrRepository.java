package com.backend.repositories;

import com.backend.entities.Assistante;
import com.backend.entities.Eleve;
import com.backend.entities.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface AssistanteAndrRepository extends JpaRepository<Assistante,Long> {

        @Query("SELECT e FROM Eleve e, Assistante a, User u, Ecole S WHERE a.user.id = u.id AND  S.id = a.ecole.id AND e.ecole.id=S.id AND u.username = ?1 AND u.type = ?2")
        Optional<List<Eleve>> FindStudentByUsernameTp(String username, UserAccountType type);

    }


