package com.backend.services;

import com.backend.dtos.TrajetDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.mappers.TrajetMapper;
import com.backend.repositories.TrajetsRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrajetsService {
    private final TrajetsRepository trajetsRepository;


    public TrajetsService(TrajetsRepository trajetsRepository) {
        this.trajetsRepository = trajetsRepository;
    }

    public Long storeTrajet(TrajetDto trajetDto) {
        var trajet = trajetsRepository.save(
                TrajetMapper.INSTANCE.trajetDtoToTrajet(trajetDto)
        );
        return trajet.getId();
    }

    public List<TrajetDto> getTrajets() {
        var trajet = trajetsRepository.findAll(Sort.by(
                Sort.Direction.DESC,
                "createdAt"
        ));

        return TrajetMapper.INSTANCE.trajetsToTrajetsDtos(trajet);
    }

    public void updateTrajet(Long id, TrajetDto trajetDto) {
        var trajet = trajetsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("no trajet was found with the provided id!")
        );

        TrajetMapper.INSTANCE.updateTrajetFromTrajetDto(trajetDto, trajet);
    }

    public void deleteTrajet(Long id) {
        var trajet = trajetsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("no trajet was found with the provided id!")
        );
        trajetsRepository.delete(trajet);
    }
}
