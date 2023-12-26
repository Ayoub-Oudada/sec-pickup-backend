package com.backend.services;

import com.backend.dtos.AssistanteDto;
import com.backend.entities.Assistante;
import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import com.backend.repositories.AssistanteRepository;
import com.backend.repositories.UsersRepository;
import com.backend.services.interfaces.AssistanteServiceInt;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class AssistanteService implements AssistanteServiceInt {

    private AssistanteRepository assistanteRepository;
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public AssistanteService(AssistanteRepository assistanteRepository, UsersRepository usersRepository, PasswordEncoder passwordEncoder)
    {
        this.assistanteRepository = assistanteRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AssistanteDto save(AssistanteDto assistantedto)
    {
        Assistante assistante = AssistanteDto.toEntity(assistantedto);

        User user = new User();
        user.setUsername(assistantedto.getEmail());
        user.setType(UserAccountType.ASSISTANTE);
        user.setPassword(passwordEncoder.encode("secpickup"));

        User savedUser = usersRepository.save(user);
        assistante.setUser(savedUser);

        Assistante savedAssistante = assistanteRepository.save(assistante);
        return  AssistanteDto.fromEntity(
                savedAssistante
        );
    }

    @Override
    public AssistanteDto findById(Long id) {
        if(id == null)
        {
            log.error("Assistante Id is null");
            return null;
        }

        return assistanteRepository.findById(id)
                .map(AssistanteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune Assistante avec l'id = "+id+ " n'est trouve dans la BDD"
                ));
    }

    @Override
    public List<AssistanteDto> findAll() {
        return assistanteRepository.findAll(Sort.by(
                        Sort.Direction.DESC,
                        "createdAt")).stream()
                .map(AssistanteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null)
        {
            log.error("Assistante Id is null");
        }
        assistanteRepository.deleteById(id);
    }


    @Override
    public AssistanteDto updateAssistante(Long id, AssistanteDto assistanteDto) {

        if( id == null)
        {
            log.error("Assistante Id is null");
            return null;
        }

        Assistante existingAssistante = assistanteRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Aucune assistante avec l'id = "+id+ " n'est trouvé dans la BDD"
                        )
                );

        existingAssistante.setNom(assistanteDto.getNom());
        existingAssistante.setPrenom(assistanteDto.getPrenom());
        existingAssistante.setCni(assistanteDto.getCni());
        existingAssistante.setEmail(assistanteDto.getEmail());

        Assistante assistante = assistanteRepository.save(existingAssistante);

        return AssistanteDto.fromEntity(assistante);

    }
}
