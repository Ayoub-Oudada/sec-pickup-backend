package com.backend.services;

import com.backend.dtos.ParentDto;
import com.backend.entities.Parent;
import com.backend.entities.User;
import com.backend.entities.UserAccountType;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.mappers.ParentMapper;
import com.backend.repositories.ParentsRepository;
import com.backend.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class ParentsService {
    private final ParentsRepository parentsRepository;
    private final MailService mailService;
    private final UsersRepository usersRepository;


    public List<ParentDto> getAllParents() {
        List<Parent> parents = parentsRepository.findAll(Sort.by(
                Sort.Direction.DESC,
                "createdAt"
        ));

        return ParentMapper.INSTANCE.parentsToParentsDtos(parents);
    }


    public Long storeParent(ParentDto parentDto) {
        var password = generatePassword();
        mailService.sendMail(
                parentDto.getEmail(),
                "Your Secpickup password",
                "Your password for login is : " + password
        );
        var user = usersRepository.save(User.builder()
                .username(parentDto.getEmail())
                .password(password)
                .type(UserAccountType.PARENT)
                .build()
        );
        Parent parent = parentsRepository.save(
                ParentMapper.INSTANCE.parentDtoToParent(parentDto)
        );
        parent.setUser(user);

        return parent.getId();
    }

    public void updateParent(Long id, ParentDto parentDto) throws ResourceNotFoundException {
        var parent = parentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no parent was found with the provided id!"));
        ParentMapper.INSTANCE.updateParentFromParentDto(parentDto, parent);

        System.out.println(parent.getId());
    }

    public ParentDto showParent(Long id) throws ResourceNotFoundException {
        var parent = parentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no parent was found with the provided id!"));
        return ParentMapper.INSTANCE.parentToParentDto(parent);
    }

    public void deleteParent(Long id) throws ResourceNotFoundException {
        var parent = parentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no parent was found with the provided id!"));
        if (parent.getUser() != null)
            usersRepository.deleteById(parent.getUser().getId());

        parentsRepository.deleteById(id);
    }


    private String generatePassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}
