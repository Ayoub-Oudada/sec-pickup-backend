package com.backend.mappers;

import com.backend.dtos.RueDto;
import com.backend.entities.Rue;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface RueMapper {
    @Named("mapWithouTrajet")
    @Mapping(target = "trajet", ignore = true)
    RueDto rueToRueDto(Rue rue);

    @IterableMapping(qualifiedByName = "mapWithouTrajet")
    List<RueDto> listRuesToListRuesDto(List<Rue> rues);
}
