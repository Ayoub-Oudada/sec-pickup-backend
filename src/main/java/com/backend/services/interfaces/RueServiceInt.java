package com.backend.services.interfaces;

import com.backend.dtos.RueDto;

import java.util.List;

public interface RueServiceInt {

    RueDto saveRue(RueDto rueDto);

    RueDto findRueById(Long id);

    List<RueDto> findAllRues();

    RueDto updateRue(Long id, RueDto rueDto);

    void deleteRue(Long id);
}
