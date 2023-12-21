package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "situations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rue extends BaseEntity {
    private String lib_rue;

    @OneToMany(mappedBy = "rue")
    private List<Eleve> eleves;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;
}
