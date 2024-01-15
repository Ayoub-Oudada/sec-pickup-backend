package com.backend.controllers;

import com.backend.dtos.AddressDto;
import com.backend.services.AddressesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressesController {
    private final AddressesService addressesService;

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(addressesService.getAllAddresses());
    }

    @PostMapping
    public ResponseEntity<Object> store(@Valid @RequestBody AddressDto request,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Long id = addressesService.storeAddress(request);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/addresses/{id}").build(id))
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        return ResponseEntity.ok(
                addressesService.getAddress(id)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody AddressDto request) {
        addressesService.updateAddress(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        addressesService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
