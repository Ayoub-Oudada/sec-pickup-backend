package com.backend.services;

import com.backend.dtos.AssistanteDto;
import com.backend.dtos.RueDto;
import com.backend.repositories.RueRepository;
import com.backend.services.interfaces.RueServiceInt;
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
                RueRepository.save(RueDto.toEntity(rueDto))
        );
    };

    @Override
    public RueDto findRueById(Long id){

        return RueDto.fromEntity(
                RueRepository.findById(id)
        );
    };

    @Override
    public List<RueDto> findAllRues(){
        return RueRepository.findAll().stream()
                .map(AssistanteDto::fromEntity)
                .collect(Collectors.toList());
    };

    @Override
    public void deleteRue(Long id){
        if(id == null)
        {
            log.error("Rue Id is null");
            return;
        }
        RueRepository.deleteById(id);
    };
}
