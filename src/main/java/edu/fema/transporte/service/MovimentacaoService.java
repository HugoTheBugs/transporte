package edu.fema.transporte.service;

import edu.fema.transporte.dto.MovimentacaoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface MovimentacaoService {

    List<MovimentacaoDto> getAllMovimentacoes();

    MovimentacaoDto getMovimentacaoById(Long id) throws RuntimeException;

    List<MovimentacaoDto> findByVeiculoId(Long veiculoId);

    List<MovimentacaoDto> findByMotoristaId(Long motoristaId);

    List<MovimentacaoDto> findByItinerarioId(Long itinerarioId);

    MovimentacaoDto createMovimentacao(MovimentacaoDto movimentacaoDto) throws RuntimeException;

    MovimentacaoDto updateMovimentacao(Long id, MovimentacaoDto movimentacaoDto) throws RuntimeException;

    void deleteMovimentacao(Long id) throws RuntimeException;

    Page<MovimentacaoDto> getAllMovimentacoes(Pageable pageable);

    Page<MovimentacaoDto> buscarComFiltros(Date dataInicio, Date dataFim, Long veiculoId, Long motoristaId, Long itinerarioId, Pageable pageable);
}
