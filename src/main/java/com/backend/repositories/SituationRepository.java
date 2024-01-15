package com.backend.repositories;

import com.backend.entities.Parent;
import com.backend.entities.Situation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationRepository extends JpaRepository<Situation, Long> {
}
