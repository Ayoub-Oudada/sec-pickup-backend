package com.backend.controllers;

import com.backend.entities.Ecole;
import com.backend.repositories.EcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// EcoleController.java
@RestController
@RequestMapping("/api/ecoles")
public class EcoleController {
    @Autowired
    private EcoleRepository ecoleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Ecole> getEcoleById(@PathVariable Long id) {
        Optional<Ecole> ecole = ecoleRepository.findById(id);
        return ecole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
