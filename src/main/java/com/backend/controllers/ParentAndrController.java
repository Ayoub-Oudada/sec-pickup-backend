package com.backend.controllers;

import com.backend.entities.Eleve;
import com.backend.entities.Parent;
import com.backend.entities.UserAccountType;
import com.backend.services.ParentsAndrService;
import com.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/parent")
public class ParentAndrController {

    private final ParentsAndrService parentsService;
    @Autowired
    public ParentAndrController(ParentsAndrService parentsService) {
        this.parentsService = parentsService;
    }

    @GetMapping("/parents")
    public List<Parent> GetParents(){
        return parentsService.GetParents();
    }
    @PostMapping
    public void registerNewParent(@RequestBody Parent parent){
        parentsService.addNewParent(parent);
    }


    @DeleteMapping( path ="{parentId}")
    public void deleteParent(@PathVariable("parentId")Long ParentId){
        parentsService.deleteParent(ParentId);
    }
    @PutMapping( path ="{parentId}")
    public void updateParent(@PathVariable("parentId")Long parentId,
                              @RequestParam(required=false) String nom,
                              @RequestParam(required=false) String prenom,
                             @RequestParam(required=false) String cni){
        parentsService.updateParent (parentId, nom,prenom,cni);
    }
    @PostMapping ("/find")
    public List<Eleve>  SearchParentByUsernameType(@RequestParam(required=false) String username, @RequestParam(required=false)  UserAccountType type ){
        List<Eleve> p = parentsService.SearchParentByUsername_Type(username,type);
        return p;
    }



}
