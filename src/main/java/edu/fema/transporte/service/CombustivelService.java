package edu.fema.transporte.service;

import edu.fema.transporte.dto.CombustivelDto;

import java.util.List;

public interface CombustivelService {

    List<CombustivelDto> getAllCombustiveis();

    void createCombustivel(CombustivelDto combustivelDto);

    CombustivelDto getCombustivelById(Long id);

    void updateCombustivel(CombustivelDto combustivelDto);

    void deleteCombustivel(Long id);
}