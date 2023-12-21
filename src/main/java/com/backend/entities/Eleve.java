package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "eleves")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eleve extends BaseEntity {
    private String nom;
    private String prenom;

    @Column(name = "date_de_naissance")
    private Date dateDeNaissance;

    private int niveau;
    private String cne;
    private String domicile;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;


    @ManyToMany(mappedBy = "eleves")
    private List<Situation> situations;

    @ManyToOne
    @JoinColumn(name = "ecole_id")
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name = "rue_id")
    private Rue rue;

}
