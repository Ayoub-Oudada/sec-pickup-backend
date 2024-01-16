package com.backend.controllers;

import com.backend.dtos.TrajetDto;
import com.backend.repositories.TrajetsRepository;
import com.backend.services.TrajetsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrajetsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrajetsService trajetsService;
    @Autowired
    private TrajetsRepository trajetsRepository;

    @Autowired
    private ObjectMapper objectMapper;
    private Faker faker = new Faker();

    @Test
    public void store_itShouldStoreAValidTrajet() throws Exception {
        var trajet = TrajetDto.builder()
                .libTrajet((faker.address().streetAddress()))
                .build();


        mockMvc.perform(post("/api/trajets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trajet)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        assertTrue(trajetsRepository.existsByLibTrajet(trajet.getLibTrajet()));
    }

    @Test
    public void store_itShouldNotStoreANotValidTrajetAndShouldReturnValidationError() throws Exception {
        var trajet = TrajetDto.builder()
                .libTrajet("")
                .build();


        mockMvc.perform(post("/api/trajets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trajet)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("validation error!")))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[?(@.field == 'libTrajet')]").exists());
    }

    @Test
    public void store_ShouldNotStoreAnExestingTrajet() throws Exception {
        var trajet = TrajetDto.builder()
                .libTrajet(trajetsRepository.findAll().get(0).getLibTrajet())
                .build();


        mockMvc.perform(post("/api/trajets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trajet)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("validation error!")))
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    public void index_itShouldReturnListOfTrajets() throws Exception {
        mockMvc.perform(get("/api/trajets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize((int) trajetsRepository.count())));
    }

    @Test
    public void update_itShouldReturnNoContentWhenUpdatingTrajet() throws Exception {
        var trajet = TrajetDto.builder()
                .libTrajet(faker.address().streetAddress())
                .build();

        mockMvc.perform(put("/api/trajets/" + trajetsRepository.findAll().get(0).getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trajet))
        ).andExpect(status().isNoContent());

        assertEquals(trajet.getLibTrajet(), trajetsRepository.findAll().get(0).getLibTrajet());
    }

    @Test
    public void update_itShouldReturn404WhenProvidingNotValidId() throws Exception {
        var trajet = TrajetDto.builder()
                .libTrajet(faker.address().streetAddress())
                .build();

        mockMvc.perform(put("/api/trajets/" + trajetsRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .get(0)
                .getId() + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trajet))
        ).andExpect(status().isNotFound());
    }

//    @Test
//    public void delete_itShouldDeleteAndReturnNoContent() throws Exception {
//        var deletedTrajet = trajetsRepository.findAll().get(0);
//
//        mockMvc.perform(delete("/api/trajets/" + deletedTrajet.getId()))
//                .andExpect(status().isNoContent());
//
//        assertFalse(trajetsRepository.existsByLibTrajet(deletedTrajet.getLibTrajet()));
//    }

    @Test
    public void delete_itShouldReturn404WhenNoTrajetFound() throws Exception {
        var deletedTrajet = trajetsRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .get(0);

        mockMvc.perform(delete("/api/trajets/" + deletedTrajet.getId() + 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message", is("no trajet was found with the provided id!")));
    }
}
