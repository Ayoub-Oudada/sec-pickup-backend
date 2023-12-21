package com.backend.repositories;

import com.backend.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetsRepository extends JpaRepository<Trajet, Long> {
    boolean existsByLibTrajet(String libRue);
}
