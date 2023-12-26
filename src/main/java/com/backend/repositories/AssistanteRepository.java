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
public interface AssistanteRepository extends JpaRepository<Assistante,Long> {


}
