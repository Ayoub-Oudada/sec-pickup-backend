package com.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "ecoles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ecole extends BaseEntity {
    private String service;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ecole")
    private List<Assistante> assistantes;

    @OneToMany(mappedBy = "ecole")
    private List<Eleve> eleves;
}
