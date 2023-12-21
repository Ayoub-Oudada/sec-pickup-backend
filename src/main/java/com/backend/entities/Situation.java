package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

enum Situations {
    DEPOSER,
    RECUPPERER,
    ABSENT
}

@Entity
@Table(name = "situations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Situation extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Situations situation;

   /* @ManyToMany
    @JoinTable(
            name = "eleves_situations",
            joinColumns = @JoinColumn(name = "eleve_id"),
            inverseJoinColumns = @JoinColumn(name = "situation_id")
    )
    private List<Eleve> eleves;*/

    @OneToMany(mappedBy = "situation")
    private List<Eleve> eleves;


}
