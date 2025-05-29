package edu.fema.transporte.service;

import edu.fema.transporte.dto.VeiculoDto;

import java.util.List;

public interface VeiculoService {
    List<VeiculoDto> getAllVeiculos();

    VeiculoDto getVeiculoById(Long id) throws RuntimeException;

    List<VeiculoDto> findByMarcaId(Long marcaId);

    List<VeiculoDto> findByCombustivelId(Long combustivelId);

    VeiculoDto createVeiculo(VeiculoDto veiculoDto) throws RuntimeException;

    VeiculoDto updateVeiculo(Long id, VeiculoDto veiculoDto) throws RuntimeException;

    void deleteVeiculo(Long id) throws RuntimeException;
}
