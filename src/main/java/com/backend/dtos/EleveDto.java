package com.backend.dtos;

import com.backend.entities.*;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class EleveDto {
    private Long id;

    @NotNull(message = "Veuillez renseigner le nom de l'eleve")
    private String nom;

    @NotNull(message = "Veuillez renseigner le prenom de l'eleve")
    private String prenom;

    @NotNull(message = "Veuillez renseigner le cne de l'eleve")
    private String cne;
    private Date dateDeNaissance;
    private int niveau;
    private String domicile;
    private String position;
    private Parent parent;
    private Situation situation;
    private Ecole ecole;
    private Rue rue;


    public static Eleve toEntity(EleveDto eleveDto) {
        if (eleveDto == null) {
            return null;
        }

        Eleve eleve = new Eleve();
        eleve.setId(eleveDto.getId());
        eleve.setNom(eleveDto.getNom());
        eleve.setPrenom(eleveDto.getPrenom());
        eleve.setCne(eleveDto.getCne());
        eleve.setDateDeNaissance(eleveDto.getDateDeNaissance());
        eleve.setNiveau(eleveDto.getNiveau());
        eleve.setDomicile(eleveDto.getDomicile());
        eleve.setPosition(eleveDto.getPosition());
        eleve.setParent(eleveDto.getParent());
        eleve.setEcole(eleveDto.getEcole());
        eleve.setSituation(eleveDto.getSituation());

        return eleve;
    }

    public static EleveDto fromEntity(Eleve eleve) {

        if (eleve == null) {
            return null;
        }

        return EleveDto.builder()
                .id(eleve.getId())
                .nom(eleve.getNom())
                .prenom(eleve.getPrenom())
                .cne(eleve.getCne())
                .dateDeNaissance(eleve.getDateDeNaissance())
                .niveau(eleve.getNiveau())
                .domicile(eleve.getDomicile())
                .position(eleve.getPosition())
                .parent(eleve.getParent())
                .ecole(eleve.getEcole())
                .situation(eleve.getSituation())
                .build();
    }
}
