package com.backend;

import com.backend.dtos.EleveDto;
import com.backend.entities.*;
import com.backend.repositories.EleveRepository;
import com.backend.services.EleveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EleveServiceTest {

    @Mock
    private EleveRepository eleveRepository;

    @InjectMocks
    private EleveService eleveService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEleve() {
        EleveDto eleveDto = createSampleEleveDto();
        Eleve savedEleve = createSampleEleve();

        when(eleveRepository.save(any())).thenReturn(savedEleve);

        EleveDto result = eleveService.save(eleveDto);

        assertEquals(eleveDto.getNom(), result.getNom());
        assertEquals(eleveDto.getPrenom(), result.getPrenom());
        assertEquals(eleveDto.getCne(), result.getCne());
        assertEquals(eleveDto.getDateDeNaissance(), result.getDateDeNaissance());
        assertEquals(eleveDto.getNiveau(), result.getNiveau());
        assertEquals(eleveDto.getDomicile(), result.getDomicile());
        assertEquals(eleveDto.getParent(), result.getParent());
        assertEquals(eleveDto.getEcole(), result.getEcole());

        verify(eleveRepository, times(1)).save(any());
    }

    @Test
    public void testFindById() {
        Long eleveId = 1L;
        Eleve eleve = createSampleEleve();

        when(eleveRepository.findById(eleveId)).thenReturn(Optional.of(eleve));

        EleveDto result = eleveService.findById(eleveId);

        assertEquals(eleve.getNom(), result.getNom());
        assertEquals(eleve.getPrenom(), result.getPrenom());
        assertEquals(eleve.getCne(), result.getCne());
        assertEquals(eleve.getDateDeNaissance(), result.getDateDeNaissance());
        assertEquals(eleve.getNiveau(), result.getNiveau());
        assertEquals(eleve.getDomicile(), result.getDomicile());
        assertEquals(eleve.getParent(), result.getParent());
        assertEquals(eleve.getEcole(), result.getEcole());

        verify(eleveRepository, times(1)).findById(eleveId);
    }

    @Test
    public void testFindAll() {
        Eleve eleve = createSampleEleve();

        when(eleveRepository.findAll()).thenReturn(Collections.singletonList(eleve));

        assertEquals(1, eleveService.findAll().size());

        verify(eleveRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        Long eleveId = 1L;

        eleveService.delete(eleveId);

        verify(eleveRepository, times(1)).deleteById(eleveId);
    }

    @Test
    public void testUpdateEleve() {
        Long eleveId = 1L;
        EleveDto updatedEleveDto = createSampleEleveDto();
        updatedEleveDto.setNom("UpdatedName");

        Eleve existingEleve = createSampleEleve();

        when(eleveRepository.findById(eleveId)).thenReturn(Optional.of(existingEleve));
        when(eleveRepository.save(any())).thenReturn(existingEleve);

        EleveDto result = eleveService.updateEleve(eleveId, updatedEleveDto);

        assertEquals(updatedEleveDto.getNom(), result.getNom());
        assertEquals(existingEleve.getPrenom(), result.getPrenom());
        assertEquals(existingEleve.getCne(), result.getCne());

        verify(eleveRepository, times(1)).findById(eleveId);
        verify(eleveRepository, times(1)).save(any());
    }

    // Add more test methods as needed

    private EleveDto createSampleEleveDto() {
        return EleveDto.builder()
                .id(1L)
                .nom("John")
                .prenom("Doe")
                .cne("123456")
//                .dateDeNaissance(new Date())
                .niveau(10)
                .domicile("Some Address")
//                .parent(new Parent())
//                .address(new Address())
//                .situations(Collections.singletonList(new Situation()))
//                .ecole(new Ecole())
                .build();
    }


    private Eleve createSampleEleve() {
        return EleveDto.toEntity(createSampleEleveDto());
    }
}

