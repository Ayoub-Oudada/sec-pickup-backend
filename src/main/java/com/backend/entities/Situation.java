package com.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "situation")
    @JsonIgnore
    private List<Eleve> eleves;


}
