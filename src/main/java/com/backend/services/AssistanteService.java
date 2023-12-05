package com.backend.services;

import com.backend.dtos.AssistanteDto;
import com.backend.repositories.AssistanteRepository;
import com.backend.services.interfaces.AssistanteServiceInt;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AssistanteService implements AssistanteServiceInt {

    private AssistanteRepository assistanteRepository;

    @Autowired
    public AssistanteService(AssistanteRepository assistanteRepository)
    {
        this.assistanteRepository = assistanteRepository;
    }

    @Override
    public AssistanteDto save(AssistanteDto assistantedto)
    {
        return  AssistanteDto.fromEntity(
                assistanteRepository.save(AssistanteDto.toEntity(assistantedto))
        );
    }

    @Override
    public AssistanteDto findById(Long id) {
        if(id == null)
        {
            log.error("Assistante Id is null");
            return null;
        }

        return assistanteRepository.findById(id)
                .map(AssistanteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune Assistante avec l'id = "+id+ " n'est trouve dans la BDD"
                ));
    }

    @Override
    public List<AssistanteDto> findAll() {
        return assistanteRepository.findAll().stream()
                .map(AssistanteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null)
        {
            log.error("Assistante Id is null");
            return;
        }
        assistanteRepository.deleteById(id);
    }
}
