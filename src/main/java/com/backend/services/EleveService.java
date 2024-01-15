package com.backend.services;


import com.backend.dtos.EleveDto;
import com.backend.dtos.SituationDto;
import com.backend.entities.Eleve;
import com.backend.entities.Situation;
import com.backend.repositories.EleveRepository;
import com.backend.repositories.SituationRepository;
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
    //private SituationRepository situationRepository;

    @Autowired
    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
       // this.situationRepository = situationRepository;
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
        if(updatedEleveDto.getNom() !=null){
        existingEleve.setNom(updatedEleveDto.getNom());}
        if(updatedEleveDto.getPrenom() !=null){
        existingEleve.setPrenom(updatedEleveDto.getPrenom());}
        if(updatedEleveDto.getCne() !=null){
        existingEleve.setCne(updatedEleveDto.getCne());}
        if(updatedEleveDto.getDateDeNaissance() !=null){
        existingEleve.setDateDeNaissance(updatedEleveDto.getDateDeNaissance());}
        if(updatedEleveDto.getNiveau() !=0){
        existingEleve.setNiveau(updatedEleveDto.getNiveau());}
        if(updatedEleveDto.getDomicile() !=null){
        existingEleve.setDomicile(updatedEleveDto.getDomicile());}
        if(updatedEleveDto.getPosition() !=null){
        existingEleve.setPosition(updatedEleveDto.getPosition());}
        if(updatedEleveDto.getParent() !=null){
        existingEleve.setParent(updatedEleveDto.getParent());}
        if(updatedEleveDto.getEcole() !=null){
        existingEleve.setEcole(updatedEleveDto.getEcole());}

        // Update the Situation field
        if (updatedEleveDto.getSituation() != null) {
            /*if (existingEleve.getSituation() == null) {
                // If the existing situation is null, create a new Situation instance
                existingEleve.setSituation(new Situation());
            }

             */
           // existingEleve.getSituation().setSituation(updatedEleveDto.getSituation().getSituation());
            existingEleve.setSituation(updatedEleveDto.getSituation());
        }

        // Save the updated Eleve entity back to the database
        Eleve updatedEleve = eleveRepository.save(existingEleve);

        // Convert the updated Eleve entity to EleveDto and return it
        return EleveDto.fromEntity(updatedEleve);
    }





}
