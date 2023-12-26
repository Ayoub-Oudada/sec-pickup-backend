package com.backend.controllers;

import com.backend.dtos.AssistanteDto;
import com.backend.entities.Eleve;
import com.backend.entities.UserAccountType;
import com.backend.services.AssistanteAndrService;
import com.backend.services.AssistanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AssistanteAndrController {

    private AssistanteAndrService assistanteAndrService;

    @Autowired
    public AssistanteAndrController(AssistanteAndrService assistanteAndrService) {
        this.assistanteAndrService = assistanteAndrService;
    }




    @PostMapping ("/assistante/find")
    public List<Eleve>  SearchStudentByAssitante(@RequestParam(required=false) String username, @RequestParam(required=false) UserAccountType type ){
        List<Eleve> p = assistanteAndrService.SearchStudentByAssitante(username,type);
        return p;
    }
}
