package com.backend.services.interfaces;

import com.backend.dtos.AssistanteDto;
import com.backend.entities.Assistante;
import java.util.List;

public interface AssistanteServiceInt {
    AssistanteDto save(AssistanteDto assistanteDto);

    AssistanteDto findById(Long id);

    List<AssistanteDto> findAll();

    void delete(Long id);
}
