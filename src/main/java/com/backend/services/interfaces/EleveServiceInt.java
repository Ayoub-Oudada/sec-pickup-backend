package com.backend.services.interfaces;

import com.backend.dtos.EleveDto;

import java.util.List;

public interface EleveServiceInt {
    EleveDto save(EleveDto eleveDto);

    EleveDto findById(Long id);

    List<EleveDto> findAll();

    void delete(Long id);
    EleveDto updateEleve(Long id, EleveDto updatedEleveDto);
}
