package com.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "trajets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trajet extends BaseEntity {
    @Column(name = "lib_trajet")
    private String libTrajet;

    @OneToMany(mappedBy = "trajet")
    private List<Address> addresses;

    @OneToMany(mappedBy = "trajet")
    private List<Assistante> assistantes;


    @OneToMany(mappedBy = "trajet")
    private List<Rue> rue;
}
