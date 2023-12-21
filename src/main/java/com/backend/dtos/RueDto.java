package com.backend.dtos;

import com.backend.entities.Assistante;
import com.backend.entities.Rue;
import com.backend.entities.Trajet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RueDto {

    private Long id;
    private String lib_rue;
    private TrajetDto trajet;

    public static Rue toEntity(RueDto rueDto) {
        if (rueDto == null) {
            return null;
        }

        Rue rue = new Rue();
        rue.setId(rueDto.getId());
        rue.setLib_rue(rueDto.getLib_rue());

        return rue;
    }

    public static RueDto fromEntity(Rue rue) {

        if (rue == null) {
            return null;
        }

        return RueDto.builder()
                .id(rue.getId())
                .lib_rue(rue.getLib_rue())
                .build();
    }



}
