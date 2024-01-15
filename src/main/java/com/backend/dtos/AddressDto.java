package com.backend.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotBlank
    private String rue;
    @NotBlank
    private String immeuble;
    @NotBlank
    private String codePostal;

    @NotNull
    private Long trajetId;

    private TrajetDto trajet;
}
