package com.backend.mappers;

import com.backend.dtos.TrajetDto;
import com.backend.entities.Trajet;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrajetMapper {

    TrajetMapper INSTANCE = Mappers.getMapper(TrajetMapper.class);

    TrajetDto trajetToTrajetDto(Trajet trajet);

    Trajet trajetDtoToTrajet(TrajetDto trajetDto);

    List<TrajetDto> trajetsToTrajetsDtos(List<Trajet> trajets);

    @Mapping(target = "id", ignore = true)
    void updateTrajetFromTrajetDto(TrajetDto trajetDto, @MappingTarget Trajet trajet);
}
