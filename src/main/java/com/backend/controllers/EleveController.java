package com.backend.controllers;

import com.backend.dtos.EleveDto;
import com.backend.services.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class EleveController {
    private EleveService eleveService;

    @Autowired
    public EleveController(EleveService eleveService)
    {

        this.eleveService = eleveService;
    }

    @PostMapping(value="/eleves/save")
    public EleveDto save(@RequestBody EleveDto eleveDto)
    {
        return
                eleveService.save(eleveDto);
    }

    @GetMapping(value="/eleves")
    public List<EleveDto> findAll()
    {

        return eleveService.findAll();
    }
    @GetMapping(value="/eleves/{id}")
    public EleveDto findById(@PathVariable Long id) {
        return eleveService.findById(id);
    }

    @DeleteMapping(value = "/eleves/delete/{id}")
    public void delete(@PathVariable Long id) {
        eleveService.delete(id);
    }

    @PutMapping(value = "/eleves/update/{id}")
    public void update(@PathVariable Long id,@RequestBody EleveDto eleveDto) {
        eleveService.updateEleve(id,eleveDto);
    }
}
