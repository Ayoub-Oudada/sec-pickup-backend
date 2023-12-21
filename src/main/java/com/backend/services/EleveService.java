package com.backend.services;


import com.backend.dtos.EleveDto;
import com.backend.entities.Eleve;
import com.backend.repositories.EleveRepository;
import com.backend.services.interfaces.EleveServiceInt;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EleveService implements EleveServiceInt {
    private EleveRepository eleveRepository;

    @Autowired
    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    @Override
    public EleveDto save(EleveDto eleveDto) {
        return EleveDto.fromEntity(
                eleveRepository.save(EleveDto.toEntity(eleveDto))
        );
    }

    @Override
    public EleveDto findById(Long id) {
        if (id == null) {
            log.error("Eleve Id is null");
            return null;
        }

        return eleveRepository.findById(id)
                .map(EleveDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Eleve avec l'id = " + id + " n'est trouve dans la BDD"
                ));
    }

    @Override
    public List<EleveDto> findAll() {
        return eleveRepository.findAll().stream()
                .map(EleveDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Eleve Id is null");
            return;
        }
        eleveRepository.deleteById(id);
    }

    @Transactional
    public EleveDto updateEleve(Long id, EleveDto updatedEleveDto) {
        // Fetch the existing Eleve entity from the database
        Eleve existingEleve = eleveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eleve not found with id: " + id));

        // Update the fields of the existing Eleve entity with the new values from the EleveDto
        existingEleve.setNom(updatedEleveDto.getNom());
        existingEleve.setPrenom(updatedEleveDto.getPrenom());
        existingEleve.setCne(updatedEleveDto.getCne());
        existingEleve.setDateDeNaissance(updatedEleveDto.getDateDeNaissance());
        existingEleve.setNiveau(updatedEleveDto.getNiveau());
        existingEleve.setDomicile(updatedEleveDto.getDomicile());
        existingEleve.setParent(updatedEleveDto.getParent());
        existingEleve.setEcole(updatedEleveDto.getEcole());

        // Save the updated Eleve entity back to the database
        Eleve updatedEleve = eleveRepository.save(existingEleve);

        // Convert the updated Eleve entity to EleveDto and return it
        return EleveDto.fromEntity(updatedEleve);
    }
}
