package edu.fema.transporte.service.impl;

import edu.fema.transporte.dto.MovimentacaoDto;
import edu.fema.transporte.entity.*;
import edu.fema.transporte.repository.ItinerarioRepository;
import edu.fema.transporte.repository.MotoristaRepository;
import edu.fema.transporte.repository.MovimentacaoRepository;
import edu.fema.transporte.repository.VeiculoRepository;
import edu.fema.transporte.service.MovimentacaoService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final MotoristaRepository motoristaRepository;
    private final ItinerarioRepository itinerarioRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<MovimentacaoDto> getAllMovimentacoes() {
        return movimentacaoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovimentacaoDto getMovimentacaoById(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada!"));
        return convertToDto(movimentacao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimentacaoDto> findByVeiculoId(Long veiculoId) {
        return movimentacaoRepository.findByVeiculoId(veiculoId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimentacaoDto> findByMotoristaId(Long motoristaId) {
        return movimentacaoRepository.findByMotoristaId(motoristaId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimentacaoDto> findByItinerarioId(Long itinerarioId) {
        return movimentacaoRepository.findByItinerarioId(itinerarioId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MovimentacaoDto createMovimentacao(MovimentacaoDto movimentacaoDto) {
        Veiculo veiculo = veiculoRepository.findById(movimentacaoDto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Motorista motorista = motoristaRepository.findById(movimentacaoDto.getMotoristaId())
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));
        Itinerario itinerario = itinerarioRepository.findById(movimentacaoDto.getItinerarioId())
                .orElseThrow(() -> new RuntimeException("Itinerário não encontrado"));

        Movimentacao movimentacao = modelMapper.map(movimentacaoDto, Movimentacao.class);
        movimentacao.setVeiculo(veiculo);
        movimentacao.setMotorista(motorista);
        movimentacao.setItinerario(itinerario);

        Movimentacao savedMovimentacao = movimentacaoRepository.save(movimentacao);
        return convertToDto(savedMovimentacao);
    }

    @Override
    @Transactional
    public MovimentacaoDto updateMovimentacao(Long id, MovimentacaoDto movimentacaoDto) {
        Movimentacao existingMovimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada!"));

        Veiculo veiculo = veiculoRepository.findById(movimentacaoDto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        Motorista motorista = motoristaRepository.findById(movimentacaoDto.getMotoristaId())
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado!"));

        Itinerario itinerario = itinerarioRepository.findById(movimentacaoDto.getItinerarioId())
                .orElseThrow(() -> new RuntimeException("Itinerário não encontrado!"));

        existingMovimentacao.setData(movimentacaoDto.getData());
        existingMovimentacao.setKmInicial(movimentacaoDto.getKmInicial());
        existingMovimentacao.setKmFinal(movimentacaoDto.getKmFinal());

        existingMovimentacao.setVeiculo(veiculo);
        existingMovimentacao.setMotorista(motorista);
        existingMovimentacao.setItinerario(itinerario);

        Movimentacao updatedMovimentacao = movimentacaoRepository.save(existingMovimentacao);

        return convertToDto(updatedMovimentacao);
    }

    @Override
    @Transactional
    public void deleteMovimentacao(Long id) {
        if(!movimentacaoRepository.existsById(id)){
            throw new RuntimeException("Movimentação não encontrada!");
        }
        movimentacaoRepository.deleteById(id);
    }

    private MovimentacaoDto convertToDto(Movimentacao movimentacao) {
        MovimentacaoDto dto = modelMapper.map(movimentacao, MovimentacaoDto.class);
        dto.setVeiculoPlaca(movimentacao.getVeiculo().getPlaca());
        dto.setMotoristaNome(movimentacao.getMotorista().getNome());
        dto.setItinerarioDescricao(movimentacao.getItinerario().getDescricao());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovimentacaoDto> getAllMovimentacoes(Pageable pageable) {
        return movimentacaoRepository.findAll(pageable)
                .map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovimentacaoDto> buscarComFiltros(Date dataInicio, Date dataFim, Long veiculoId, Long motoristaId, Long itinerarioId, Pageable pageable) {
        return movimentacaoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dataInicio != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("data"), dataInicio));
            }

            if (dataFim != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("data"), dataFim));
            }

            if (veiculoId != null) {
                predicates.add(cb.equal(root.get("veiculo").get("id"), veiculoId));
            }

            if (motoristaId != null) {
                predicates.add(cb.equal(root.get("motorista").get("id"), motoristaId));
            }

            if (itinerarioId != null) {
                predicates.add(cb.equal(root.get("itinerario").get("id"), itinerarioId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(this::convertToDto);
    }
}