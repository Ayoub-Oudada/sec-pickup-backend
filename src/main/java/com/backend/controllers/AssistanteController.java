package com.backend.controllers;


import com.backend.dtos.AssistanteDto;
import com.backend.services.AssistanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class AssistanteController {
    private AssistanteService assistanteService;

    @Autowired
    public AssistanteController(AssistanteService assistanteService)
    {
        this.assistanteService = assistanteService;
    }

    @PostMapping(value="/assistantes")
    @ResponseStatus(HttpStatus.CREATED)
    public AssistanteDto save(@RequestBody AssistanteDto assistanteDto)
    {
       return assistanteService.save(assistanteDto);
    }

    @GetMapping(value="/assistantes")
    public List<AssistanteDto> findAll()
    {
        return assistanteService.findAll();
    }
}
