package com.backend.controllers;

import com.backend.services.EleveAndrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/eleve/maj/")
public class EleveAndrController {

    private final EleveAndrService eleveAndrService;

    @Autowired
    public EleveAndrController(EleveAndrService eleveAndrService) {
        this.eleveAndrService = eleveAndrService;
    }

    @PutMapping("{eleveId}")
    public void updateElevePosi(@PathVariable Long eleveId,
                               @RequestParam(required = false) String newpos) {
        eleveAndrService.updateEleve(newpos,eleveId);
    }
}
