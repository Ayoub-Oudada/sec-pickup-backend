package com.backend.controllers;

import com.backend.dtos.AddressDto;
import com.backend.dtos.ParentDto;
import com.backend.services.AddressesService;
import com.backend.services.ParentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressesService addressesService;

    @Autowired
    private ObjectMapper objectMapper;
    private Faker faker = new Faker();


    @Test
    public void itCanGetAllParents() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .rue(faker.address().streetName())
                .codePostal(faker.address().countryCode())
                .immeuble("33")
                .trajetId(3L)
                .build();
        when(addressesService.getAllAddresses()).thenReturn(List.of(addressDto));

        mockMvc
                .perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].immeuble", is("33")))
                .andExpect(jsonPath("$[0].rue", is(addressDto.getRue())));
    }


    @Test
    public void itCanShowAnAddress() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .rue(faker.address().streetName())
                .codePostal(faker.address().countryCode())
                .immeuble("33")
                .trajetId(3L)
                .build();

        when(addressesService.getAddress(1L)).thenReturn(addressDto);
        mockMvc
                .perform(get("/api/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.immeuble", is("33")))
                .andExpect(jsonPath("$.rue", is(addressDto.getRue())));
    }

    @Test
    public void itCanCreateANewParent() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .rue(faker.address().streetName())
                .codePostal(faker.address().countryCode())
                .immeuble("33")
                .trajetId(3L)
                .build();

        when(addressesService.storeAddress(addressDto)).thenReturn(1L);
        mockMvc
                .perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/addresses/1"));
    }

    @Test
    public void itShouldUpdateAddressWhenRequestIsValid() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .rue(faker.address().streetName())
                .codePostal(faker.address().countryCode())
                .immeuble("33")
                .trajetId(3L)
                .build();

        doNothing().when(addressesService).updateAddress(1L, addressDto);

        mockMvc.perform(put("/api/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void itShouldReturnNoContentResponseWhenDeletingAddress() throws Exception {

        mockMvc.perform(delete("/api/addresses/1"))
                .andExpect(status().isNoContent());
    }
}
