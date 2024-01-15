package com.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
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

    public Situation(Situations situations) {
        this.situation =situations;
    }

    // Public method to access Situations enum values
    @JsonValue
    public Situations getSituation() {
        return situation;
    }

    // Constructor or static factory method for Jackson deserialization
    public static Situation fromString(String value) {
        return new Situation(Situations.valueOf(value));
    }


}
