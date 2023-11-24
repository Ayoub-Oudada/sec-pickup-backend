package com.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "administration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administration extends BaseEntity {
    private String service;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
