package com.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String position;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Parent parent;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "situation_id")
    //@JsonBackReference
    //@JsonIgnore
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "ecole_id")
    @JsonIgnore
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name = "rue_id")
    @JsonIgnore
    private Rue rue;

}
