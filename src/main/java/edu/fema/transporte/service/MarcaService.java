package edu.fema.transporte.service;

import edu.fema.transporte.dto.MarcaDto;

import java.util.List;

public interface MarcaService {

    List<MarcaDto> getAllMarcas();

    void createMarca(MarcaDto marcaDto);

    MarcaDto getMarcaById(Long id);

    void updateMarca(MarcaDto marcaDto);

    void deleteMarca(Long id);
}
