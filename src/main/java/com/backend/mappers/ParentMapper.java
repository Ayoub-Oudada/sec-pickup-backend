package com.backend.mappers;

import com.backend.dtos.ParentDto;
import com.backend.entities.Parent;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ParentMapper {
    ParentMapper INSTANCE = Mappers.getMapper(ParentMapper.class);

    List<ParentDto> parentsToParentsDtos(List<Parent> parents);

    //    @Mapping(target = "id", ignore = true)
    Parent parentDtoToParent(ParentDto parentDto);

    ParentDto parentToParentDto(Parent parent);

    @Mapping(target = "id", ignore = true)
    void updateParentFromParentDto(ParentDto parentDto, @MappingTarget Parent parent);
}
