package com.backend.controllers;

import com.backend.dtos.AssistanteDto;
import com.backend.services.AssistanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( "/api")
public class AssistanteController {
    private AssistanteService assistanteService;

    @Autowired
    public AssistanteController(AssistanteService assistanteService) {
        this.assistanteService = assistanteService;
    }

    @PostMapping(value = "/assistantes")
    @ResponseStatus(HttpStatus.CREATED)
    public AssistanteDto save(@RequestBody AssistanteDto assistanteDto) {
        return assistanteService.save(assistanteDto);
    }

    @GetMapping(value = "/assistantes/{idAssistante}")
    public AssistanteDto findById(@PathVariable("idAssistante") Long idAssistante){
        return assistanteService.findById(idAssistante);
    }

    @GetMapping(value="/assistantes")
    public List<AssistanteDto> findAll()
    {
        return assistanteService.findAll();
    }

    @PutMapping(value = "/assistantes/{idAssistante}")
    public AssistanteDto updateAssistante(@PathVariable("idAssistante") Long id, @Valid @RequestBody AssistanteDto assistanteDto) {
        return assistanteService.updateAssistante(id,assistanteDto);
    }

    @DeleteMapping("/assistantes/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        assistanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
