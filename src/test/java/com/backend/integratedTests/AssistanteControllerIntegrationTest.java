package com.backend.integratedTests;

import com.backend.controllers.AssistanteController;
import com.backend.dtos.AssistanteDto;
import com.backend.services.AssistanteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class AssistanteControllerIntegrationTest {


    @InjectMocks
    private AssistanteController assistanteController;

    @MockBean
    private AssistanteService assistanteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private Faker faker = new Faker();

    @Test
    public void itCanGetAllAssistantes() throws Exception {
        AssistanteDto assistante = createAssistante(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin()
        );

        when(assistanteService.findAll()).thenReturn(List.of(assistante));

        mockMvc
                .perform(get("/api/assistantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].nom", is(assistante.getNom())))
                .andExpect(jsonPath("$[0].cni", is(assistante.getCni())));
    }

    @Test
    public void itCanCreateANewAssistante() throws Exception {
        AssistanteDto assistante = createAssistante(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin()
        );
        System.out.println(assistante);

        when(assistanteService.save(assistante)).thenReturn(null);

        mockMvc
                .perform(post("/api/assistantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assistante)))
                .andExpect(status().isCreated());

    }

    private AssistanteDto createAssistante(String prenom,
                                           String nom,
                                           String cni) {
        return AssistanteDto.builder()
                .nom(nom)
                .prenom(prenom)
                .cni(cni)
                .build();
    }
}
