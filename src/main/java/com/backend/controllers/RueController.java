package com.backend.controllers;

import com.backend.dtos.RueDto;
import com.backend.services.RueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rues")
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


    @GetMapping(value = "/rues")
    @ResponseStatus(HttpStatus.OK)
    public List<RueDto> findAllRues(){
        return rueService.findAllRues();
    }



}
