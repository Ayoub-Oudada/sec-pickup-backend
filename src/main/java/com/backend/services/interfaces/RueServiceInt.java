package com.backend.services.interfaces;

import com.backend.dtos.RueDto;

import java.util.List;

public interface RueServiceInt {

    RueDto saveRue(RueDto rueDto);

    RueDto findRueById(Long id);

    List<RueDto> findAllRues();

    void deleteRue(Long id);
}
