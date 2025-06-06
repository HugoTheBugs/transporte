package edu.fema.transporte.service;
import edu.fema.transporte.dto.AbastecimentoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
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

    Page<AbastecimentoDto> getAllAbastecimentos(Pageable pageable);

    Page<AbastecimentoDto> buscarComFiltros(Date dataInicio, Date dataFim, Long veiculoId, Long motoristaId, Long postoId, Pageable pageable);

}
