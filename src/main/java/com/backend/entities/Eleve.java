package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
