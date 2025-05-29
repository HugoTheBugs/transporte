package edu.fema.transporte.service;
import edu.fema.transporte.dto.AbastecimentoDto;

import java.util.List;

public interface AbastecimentoService {

    List<AbastecimentoDto> getAllAbastecimentos();

    AbastecimentoDto getAbastecimentoById(Long id) throws RuntimeException;

    List<AbastecimentoDto> findByPostoId(Long postoId);

    List<AbastecimentoDto> findByMotoristaId(Long motoristaId);

    List<AbastecimentoDto> findByVeiculoId(Long veiculoId);

    List<AbastecimentoDto> findByCombustivelId(Long combustivelId);

    AbastecimentoDto createAbastecimento(AbastecimentoDto abastecimentoDto) throws RuntimeException;

    AbastecimentoDto updateAbastecimento(Long id, AbastecimentoDto abastecimentoDto) throws RuntimeException;

    void deleteAbastecimento(Long id) throws RuntimeException;
}
