package com.backend.repositories;


import com.backend.entities.Assistante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanteRepository extends JpaRepository<Assistante,Long> {
}
