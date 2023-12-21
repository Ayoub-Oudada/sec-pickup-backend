package com.backend.dtos;

import com.backend.UniqueField;
import com.backend.entities.Assistante;
import com.backend.entities.Rue;
import com.backend.repositories.TrajetsRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrajetDto {
    private Long id;

    @NotBlank
    @UniqueField(message = "a trajet with this name already exists", fieldName = "libTrajet", repository = TrajetsRepository.class)
    private String libTrajet;

    private List<Assistante> assistantes;

    private List<Rue> rue;
}
