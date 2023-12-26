package com.backend.repositories;

import com.backend.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleveRepository extends JpaRepository<Eleve,Long> {
}
