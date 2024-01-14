package com.backend.controllers;

import com.backend.entities.Ecole;
import com.backend.repositories.EcoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EcoleControllerTest {

    @Mock
    private EcoleRepository ecoleRepository;

    @InjectMocks
    private EcoleController ecoleController;

    @Test
    void testGetEcoleById() throws Exception {
        // Create a sample Ecole
        Ecole sampleEcole = new Ecole();
        sampleEcole.setId(1L);
        sampleEcole.setNom("Sample Ecole");

        // Mock the behavior of the ecoleRepository
        when(ecoleRepository.findById(1L)).thenReturn(Optional.of(sampleEcole));

        // Set up the mockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ecoleController).build();

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ecoles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Sample Ecole"));
    }
}

