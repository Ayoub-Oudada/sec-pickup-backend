package com.backend.controllers;

import com.backend.dtos.ParentDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.services.ParentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParentsController.class)
@RunWith(SpringRunner.class)
public class ParentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParentsService parentsService;

    @Autowired
    private ObjectMapper objectMapper;
    private Faker faker = new Faker();


    @Test
    public void itCanGetAllParents() throws Exception {
        ParentDto parent = createParent(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin(),
                faker.number().digits(5),
                faker.internet().emailAddress(),
                1L);
        when(parentsService.getAllParents()).thenReturn(List.of(parent));

        mockMvc
                .perform(get("/api/parents/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is(parent.getEmail())))
                .andExpect(jsonPath("$[0].cni", is(parent.getCni())));
    }

    @Test
    public void itCanCreateANewParent() throws Exception {
        ParentDto parent = createParent(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin(),
                faker.number().digits(5),
                faker.internet().emailAddress(),
                1L);

        when(parentsService.storeParent(parent)).thenReturn(1L);
        mockMvc
                .perform(post("/api/parents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parent)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/parents/1"));
    }

    @Test
    public void itShouldReturnAnErrorResponseWhenParentRequestIsNotValid() throws Exception {
        ParentDto parent = ParentDto.builder()
                .id(1L)
                .nom("test")
                .build();

        mockMvc
                .perform(post("/api/parents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("validation error!")))
                .andExpect(jsonPath("$.errors", hasSize(4)))
                .andExpect(jsonPath("$.errors[?(@.field == 'cni')]").exists())
                .andExpect(jsonPath("$.errors[?(@.field == 'email')]").exists());

    }

    @Test
    public void itCanShowAParent() throws Exception {
        ParentDto parent = createParent(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin(),
                faker.number().digits(5),
                faker.internet().emailAddress(),
                1L);

        when(parentsService.showParent(1L)).thenReturn(parent);

        mockMvc
                .perform(get("/api/parents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(parent.getEmail())))
                .andExpect(jsonPath("$.cni", is(parent.getCni())))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void itShouldReturn404WhenIdIsUnknown() throws Exception {
        when(parentsService.showParent(2L)).thenThrow(ResourceNotFoundException.class);

        mockMvc
                .perform(get("/api/parents/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void itShouldReturnNoContentResponseWhenDeletingParent() throws Exception {

        mockMvc.perform(delete("/api/parents/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void itShouldUpdateParentWhenRequestIsValid() throws Exception {
        ParentDto parent = createParent(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin(),
                faker.number().digits(5),
                faker.internet().emailAddress(),
                1L);

        doNothing().when(parentsService).updateParent(1L, parent);

        mockMvc.perform(put("/api/parents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parent)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void itShouldReturn404WhenTryingUpdatingANonExistingParent() throws Exception {
        ParentDto parent = createParent(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().asin(),
                faker.number().digits(5),
                faker.internet().emailAddress(),
                1L);

        doThrow(new ResourceNotFoundException("no parent was found with the provided id!"))
                .when(parentsService)
                .updateParent(1L, parent);

        mockMvc.perform(put("/api/parents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parent)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("no parent was found with the provided id!")));
    }

    @Test
    public void itShouldReturn404NotFoundWhenDeletingANotExistingParent() throws Exception {
        doThrow(new ResourceNotFoundException("no parent was found with the provided id!"))
                .when(parentsService)
                .deleteParent(1L);

        mockMvc.perform(delete("/api/parents/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("no parent was found with the provided id!")));
    }

    @Test
    public void itShouldReturn400BadRequestWhenTryingToUpdateParentWithInvalidParentRequest() throws Exception {

        ParentDto parent = ParentDto.builder().nom("test").build();

        mockMvc.perform(put("/api/parents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("validation error!")))
                .andExpect(jsonPath("$.errors", hasSize(4)))
                .andExpect(jsonPath("$.errors[?(@.field == 'cni')]").exists())
                .andExpect(jsonPath("$.errors[?(@.field == 'email')]").exists());
    }

    private ParentDto createParent(String prenom,
                                   String nom,
                                   String cni,
                                   String tph,
                                   String email,
                                   Long id
    ) {
        return ParentDto.builder()
                .id(id)
                .nom(nom)
                .prenom(prenom)
                .cni(cni)
                .email(email)
                .tph(tph)
                .build();
    }


}