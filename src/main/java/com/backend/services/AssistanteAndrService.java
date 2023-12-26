package com.backend.services;

import com.backend.entities.Eleve;
import com.backend.entities.Parent;
import com.backend.entities.UserAccountType;
import com.backend.repositories.AssistanteAndrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssistanteAndrService {
    private final AssistanteAndrRepository assistanteAndrRepository;

    @Autowired
    public AssistanteAndrService(AssistanteAndrRepository assistanteAndrRepository) {
        this.assistanteAndrRepository = assistanteAndrRepository;
    }



    public List<Eleve> SearchStudentByAssitante( String username,UserAccountType type ) {

        Optional<List<Eleve>> parentOptional =assistanteAndrRepository.FindStudentByUsernameTp(username,type);
        if( parentOptional.isPresent()){
            List<Eleve> s = parentOptional.get();
            return s;

        }else{throw new IllegalStateException("Erreur");}

    }

}
