package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    private String rue;
    private String immeuble;
    private String codePostal;

    @OneToMany(mappedBy = "address")
    private List<Eleve> eleves;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;
}

