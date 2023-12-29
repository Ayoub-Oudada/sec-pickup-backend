package com.backend.controllers;

import com.backend.dtos.AssistanteDto;
import com.backend.dtos.RueDto;
import com.backend.services.RueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RueController {

    private final RueService rueService;

    @Autowired
    public RueController(RueService rueService){this.rueService = rueService;}


    @PostMapping(value = "/rues")
    @ResponseStatus(HttpStatus.CREATED)
    public RueDto saveRue(@RequestBody RueDto rueDto)
    {
        return rueService.saveRue(rueDto);
    }


    @GetMapping(value = "/rues/{idRue}")
    public RueDto findById(@PathVariable("idRue") Long idRue){
        return rueService.findRueById(idRue);
    }

    @GetMapping(value = "/rues")
    @ResponseStatus(HttpStatus.OK)
    public List<RueDto> findAllRues(){
        return rueService.findAllRues();
    }

    @PutMapping("/rues/{id}")
    public ResponseEntity<RueDto> updateRue(@PathVariable Long id, @RequestBody RueDto updatedRueDto) {
        RueDto updatedRue = rueService.updateRue(id, updatedRueDto);
        return ResponseEntity.ok().body(updatedRue);
    }

    @DeleteMapping("/rues/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        rueService.deleteRue(id);
        return ResponseEntity.noContent().build();
    }
}
