package com.backend.controllers;

import com.backend.dtos.ParentDto;
import com.backend.services.ParentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentsController {

    private final ParentsService parentsService;

    @Autowired
    public ParentsController(ParentsService parentsService) {
        this.parentsService = parentsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ParentDto>> index() {
        return ResponseEntity.ok(parentsService.getAllParents());
    }

    @PostMapping
    public ResponseEntity<Object> store(@Valid @RequestBody ParentDto request,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Long id = parentsService.storeParent(request);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/parents/{id}").build(id))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ParentDto request) {
        parentsService.updateParent(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(parentsService.showParent(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        parentsService.deleteParent(id);

        return ResponseEntity.noContent().build();
    }
}

