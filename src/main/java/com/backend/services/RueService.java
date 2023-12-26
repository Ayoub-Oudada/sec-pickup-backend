package com.backend.services;

import com.backend.dtos.AssistanteDto;
import com.backend.dtos.RueDto;
import com.backend.dtos.TrajetDto;
import com.backend.entities.Rue;
import com.backend.repositories.RueRepository;
import com.backend.services.interfaces.RueServiceInt;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RueService implements RueServiceInt {

    public RueRepository rueRepository;

    @Autowired
    public RueService(RueRepository rueRepository){ this.rueRepository = rueRepository;}

    @Override
    public RueDto saveRue(RueDto rueDto){
        return RueDto.fromEntity(
                rueRepository.save(RueDto.toEntity(rueDto))
        );
    }

    @Override
    public RueDto findRueById(Long id){

        return rueRepository.findById(id)
                .map(RueDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune rue avec l'ID = "+id+" n'est trouve dans la BDD"
                ));
    }

    @Override
    public List<RueDto> findAllRues(){
        return rueRepository.findAll().stream()
                .map(RueDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public RueDto updateRue(Long id, RueDto rueDto){
        if (id == null || rueDto == null) {
            throw new IllegalArgumentException("Invalid input for updating Rue");
        }

        Rue existingRue = rueRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Rue with id " + id + " not found")
                );

        existingRue.setLib_rue(rueDto.getLib_rue());
        Rue updatedRue = rueRepository.save(existingRue);

        return RueDto.fromEntity(updatedRue);

    }

    @Override
    public void deleteRue(Long id){
        if(id == null)
        {
            log.error("Rue Id is null");
            return;
        }
        rueRepository.deleteById(id);
    }
}
