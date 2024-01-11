package com.backend.controllers;

import com.backend.dtos.TrajetDto;
import com.backend.services.TrajetsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/api/trajets")
@RequiredArgsConstructor
public class TrajetsController {
    private final TrajetsService trajetsService;


    @PostMapping
    public ResponseEntity<Void> store(@Valid @RequestBody TrajetDto request, UriComponentsBuilder uriComponentsBuilder) {
        Long id = trajetsService.storeTrajet(request);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/trajets/{id}").build(id))
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(
                trajetsService.getTrajets()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(
                trajetsService.getTrajet(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody TrajetDto trajetDto, @PathVariable Long id) {
        trajetsService.updateTrajet(id, trajetDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        trajetsService.deleteTrajet(id);

        return ResponseEntity.noContent().build();
    }
}
