package com.backend.repositories;

import com.backend.entities.Rue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RueRepository extends JpaRepository<Rue, Long> {
    List<Rue> findAllByTrajetIsNull();
}
