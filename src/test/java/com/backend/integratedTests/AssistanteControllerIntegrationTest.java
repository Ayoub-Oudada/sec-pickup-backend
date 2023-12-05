package com.backend.integratedTests;

import com.backend.dtos.AssistanteDto;
import com.backend.repositories.AssistanteRepository;
import com.backend.services.interfaces.AssistanteServiceInt;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssistanteControllerIntegrationTest {

    @Mock
    private AssistanteRepository assistanteRepository;

    @InjectMocks
    private AssistanteServiceInt assistanteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        AssistanteDto assistanteDto = new AssistanteDto(); // create a sample DTO

        when(assistanteRepository.save(any())).thenReturn(AssistanteDto.toEntity(assistanteDto));

        AssistanteDto savedAssistanteDto = assistanteService.save(assistanteDto);

        assertEquals(assistanteDto, savedAssistanteDto);

        verify(assistanteRepository, times(1)).save(any());
    }
}
