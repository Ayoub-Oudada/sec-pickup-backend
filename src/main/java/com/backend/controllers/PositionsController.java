package com.backend.controllers;

import com.backend.entities.Parent;
import com.backend.entities.Positions;
import com.backend.entities.User;
import com.backend.services.PositionService;
import com.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/api/position")
public class PositionsController {
    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public void registerPosition(@RequestBody Positions positions) {
        positionService.addPosition(positions);
    }

    @GetMapping("/trajet")
    public List<Positions> GetTrajet(@RequestParam String username){
        return positionService.SearchPositionByUsername(username);
    }

    @GetMapping("/last")
    public Positions GetPosition(@RequestParam String username){
        return positionService.LastPosition(username);
    }

    @GetMapping("/distance")
    public double GetDistance(@RequestParam String username1,@RequestParam String username2){
        return positionService.calculateHaversineDistance(username1,username2);
    }
}
