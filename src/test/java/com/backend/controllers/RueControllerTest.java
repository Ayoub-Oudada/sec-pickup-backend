package com.backend.controllers;


import com.backend.dtos.AssistanteDto;
import com.backend.dtos.ParentDto;
import com.backend.dtos.RueDto;
import com.backend.entities.Rue;
import com.backend.services.AssistanteService;
import com.backend.services.RueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class RueControllerTest {

    @InjectMocks
    private RueController rueController;

    @MockBean
    private RueService rueService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Faker faker = new Faker();


    @Test
    public void itCanGetAllRues() throws Exception {
        RueDto rue = createRue(
                faker.address().fullAddress()
        );

        when(rueService.findAllRues()).thenReturn(List.of(rue));

        mockMvc
                .perform(get("/api/rues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].lib_rue", is(rue.getLib_rue())));
    }


    @Test
    public void itCanCreateRue() throws Exception{

        RueDto rue = createRue(
                faker.address().fullAddress()
        );

        when(rueService.saveRue(rue)).thenReturn(rue);

        ResultActions response = mockMvc
                .perform(post("/api/rues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rue)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void itCanUpdateRue() throws Exception{

        RueDto rueDto = createRue(faker.address().fullAddress());

        when(rueService.updateRue(1L, rueDto)).thenReturn(null);

        mockMvc.perform(put("/api/rues/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rueDto)))
                .andExpect(status().isOk());
    }
    private RueDto createRue(String lib_rue) {
        return RueDto.builder()
                .lib_rue(lib_rue)
                .build();
    }

}
