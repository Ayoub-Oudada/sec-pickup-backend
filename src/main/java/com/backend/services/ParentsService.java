package com.backend.services;

import com.backend.dtos.ParentDto;
import com.backend.entities.Parent;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.mappers.ParentMapper;
import com.backend.repositories.ParentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParentsService {
    private final ParentsRepository parentsRepository;

    @Autowired
    public ParentsService(ParentsRepository parentsRepository) {
        this.parentsRepository = parentsRepository;
    }

    public List<ParentDto> getAllParents() {
        List<Parent> parents = parentsRepository.findAll(Sort.by(
                Sort.Direction.DESC,
                "createdAt"
        ));

        return ParentMapper.INSTANCE.parentsToParentsDtos(parents);
    }


    public Long storeParent(ParentDto parentDto) {
        Parent parent = parentsRepository.save(
                ParentMapper.INSTANCE.parentDtoToParent(parentDto)
        );
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
        parentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no parent was found with the provided id!"));

        parentsRepository.deleteById(id);
    }



}
