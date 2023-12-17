package com.backend.dtos;

import com.backend.entities.Assistante;
import com.backend.entities.Ecole;
import com.backend.entities.Trajet;
import com.backend.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AssistanteDto {
    private Long id;

    @NotNull(message = "Veuillez renseigner le nom de l'assistante")
    private String nom;

    @NotNull(message = "Veuillez renseigner le prenom de l'assistante")
    private String prenom;

    @NotNull(message = "Veuillez renseigner le cni de l'assistante")
    private String cni;

    private Ecole ecole;
    private User user;
    private Trajet trajet;

    public static Assistante toEntity(AssistanteDto assistanteDto) {
        if (assistanteDto == null) {
            return null;
        }

        Assistante assistante = new Assistante();
        assistante.setId(assistanteDto.getId());
        assistante.setNom(assistanteDto.getNom());
        assistante.setPrenom(assistanteDto.getPrenom());
        assistante.setCni(assistanteDto.getCni());
        assistante.setEcole(assistanteDto.getEcole());
        assistante.setUser(assistanteDto.getUser());
        assistante.setTrajet(assistanteDto.getTrajet());

        return assistante;
    }

    public static AssistanteDto fromEntity(Assistante assistante) {

        if (assistante == null) {
            return null;
        }

        return AssistanteDto.builder()
                .id(assistante.getId())
                .nom(assistante.getNom())
                .prenom(assistante.getPrenom())
                .cni(assistante.getCni())
                .ecole(assistante.getEcole())
                .user(assistante.getUser())
                .trajet(assistante.getTrajet())
                .build();
    }
}
