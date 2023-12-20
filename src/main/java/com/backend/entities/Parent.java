package com.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "parents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parent extends BaseEntity {
    private String nom;
    private String prenom;
    private String cni;
    private String tph;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    private List<Eleve> eleves;
}
