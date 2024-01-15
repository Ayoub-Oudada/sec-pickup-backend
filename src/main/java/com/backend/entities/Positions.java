package com.backend.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Positions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Positions extends BaseEntity{

    private double latitude;
    private double longitude;

    private String username;
}
