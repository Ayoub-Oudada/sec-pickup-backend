package com.backend.services;

import com.backend.entities.Eleve;
import com.backend.entities.Parent;
import com.backend.entities.UserAccountType;
import com.backend.repositories.ParentAndrRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParentsAndrService {
    private final ParentAndrRepository parentRepository;

    @Autowired
    public ParentsAndrService(ParentAndrRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public List<Parent> GetParents(){
        return parentRepository.findAll();

    }
    public void addNewParent(Parent parent){
        Optional<Parent> parentOptional=parentRepository
                .findParentByCNI(parent.getCni());
        if(parentOptional.isPresent()){
            throw new IllegalStateException("Parent allready exist");
        }
        parentRepository.save(parent);

    }

    public void deleteParent(Long parentId) {
        boolean exists =parentRepository.existsById(parentId);
        if(!exists){
            throw new IllegalStateException("Parent with id "+parentId + "does not exists");
        }
        parentRepository.deleteById(parentId);
    }
    @Transactional
    public void updateParent(Long parentId, String name, String prenom, String cin) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(()->new IllegalStateException("Parent with id "+ parentId+" does not existe"));

        if(name !=null &&
                name.length()>0 &&
                !Objects.equals(parent.getNom(),name)){
                     parent.setNom(name);
        }

        if((cin !=null) && (cin.length()>0) && (!Objects.equals(parent.getCni(),cin))){
            Optional<Parent> parentoptional=parentRepository.findParentByCNI(cin);
            if(parentoptional.isPresent()){
                throw new IllegalStateException("CNI taken");
            }

            parent.setCni(cin);
        }


        if(prenom !=null &&
                prenom.length()>0 &&
                !Objects.equals(parent.getPrenom(),prenom)){


            parent.setPrenom(prenom);
        }
    }

    public List<Eleve> SearchParentByUsername_Type( String username,UserAccountType type ) {

        Optional<List<Eleve>> parentOptional =parentRepository.FindParentByUsernameTp (username,type);
        if( parentOptional.isPresent()){
            List<Eleve> e = parentOptional.get();
           return e;
            //
        }else{throw new IllegalStateException("Erreur");}
        //usersRepository.deleteById(userId);

    }

}
