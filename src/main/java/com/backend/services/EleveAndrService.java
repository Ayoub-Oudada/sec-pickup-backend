package com.backend.services;

import com.backend.entities.Eleve;
import com.backend.entities.Parent;
import com.backend.repositories.EleveRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EleveAndrService {

    private final EleveRepository eleveAndrRepository;

    @Autowired
    public EleveAndrService(EleveRepository eleveAndrRepository) {
        this.eleveAndrRepository = eleveAndrRepository;
    }


    @Transactional
    public void updateEleve(String newposition, Long eleveId) {
        Eleve eleve = eleveAndrRepository.findById(eleveId)
                .orElseThrow(() -> new IllegalStateException("Eleve with id " + eleveId + " does not exist"));

        if (newposition != null) {
            eleve.setPosition(newposition);

            eleveAndrRepository.updateElevePos(newposition ,eleveId);
        }
    }

}
