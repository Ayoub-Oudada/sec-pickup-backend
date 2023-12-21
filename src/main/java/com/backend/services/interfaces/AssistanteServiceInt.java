package com.backend.services.interfaces;

import com.backend.dtos.AssistanteDto;
import com.backend.entities.Assistante;
import java.util.List;

public interface AssistanteServiceInt {
    AssistanteDto save(AssistanteDto assistanteDto);

    AssistanteDto findById(Long id);

    AssistanteDto updateAssistante(Long id, AssistanteDto assistanteDto);

    List<AssistanteDto> findAll();

    void delete(Long id);
}
