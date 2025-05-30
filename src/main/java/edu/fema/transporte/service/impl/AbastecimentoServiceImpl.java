package edu.fema.transporte.service.impl;

import edu.fema.transporte.dto.AbastecimentoDto;
import edu.fema.transporte.entity.*;
import edu.fema.transporte.repository.*;
import edu.fema.transporte.service.AbastecimentoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbastecimentoServiceImpl implements AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final PostoRepository postoRepository;
    private final MotoristaRepository motoristaRepository;
    private final VeiculoRepository veiculoRepository;
    private final CombustivelRepository combustivelRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AbastecimentoDto> getAllAbastecimentos() {
        return abastecimentoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AbastecimentoDto getAbastecimentoById(Long id) {
        Abastecimento abastecimento = abastecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abastecimento não encontrado!"));
        return convertToDto(abastecimento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbastecimentoDto> findByPostoId(Long postoId) {
        return abastecimentoRepository.findByPostoId(postoId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbastecimentoDto> findByMotoristaId(Long motoristaId) {
        return abastecimentoRepository.findByMotoristaId(motoristaId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbastecimentoDto> findByVeiculoId(Long veiculoId) {
        return abastecimentoRepository.findByVeiculoId(veiculoId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbastecimentoDto> findByCombustivelId(Long combustivelId) {
        return abastecimentoRepository.findByCombustivelId(combustivelId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AbastecimentoDto createAbastecimento(AbastecimentoDto abastecimentoDto) {
        Posto posto = postoRepository.findById(abastecimentoDto.getPostoId())
                .orElseThrow(() -> new RuntimeException("Posto não encontrado!"));
        Motorista motorista = motoristaRepository.findById(abastecimentoDto.getMotoristaId())
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado!"));
        Veiculo veiculo = veiculoRepository.findById(abastecimentoDto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
        Combustivel combustivel = combustivelRepository.findById(abastecimentoDto.getCombustivelId())
                .orElseThrow(() -> new RuntimeException("Combustível não encontrado!"));

        Abastecimento abastecimento = modelMapper.map(abastecimentoDto, Abastecimento.class);
        abastecimento.setPosto(posto);
        abastecimento.setMotorista(motorista);
        abastecimento.setVeiculo(veiculo);
        abastecimento.setCombustivel(combustivel);

        Abastecimento savedAbastecimento = abastecimentoRepository.save(abastecimento);
        return convertToDto(savedAbastecimento);
    }

    @Override
    @Transactional
    public AbastecimentoDto updateAbastecimento(Long id, AbastecimentoDto abastecimentoDto) {
        Abastecimento existingAbastecimento = abastecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abastecimento não encontrado!"));

        existingAbastecimento.setData(abastecimentoDto.getData());
        existingAbastecimento.setKmAtual(abastecimentoDto.getKmAtual());
        existingAbastecimento.setLitros(abastecimentoDto.getLitros());
        existingAbastecimento.setValorTotal(abastecimentoDto.getValorTotal());

        if (!existingAbastecimento.getPosto().getId().equals(abastecimentoDto.getPostoId())) {
            Posto posto = postoRepository.findById(abastecimentoDto.getPostoId())
                    .orElseThrow(() -> new RuntimeException("Posto não encontrado!"));
            existingAbastecimento.setPosto(posto);
        }

        if (!existingAbastecimento.getMotorista().getId().equals(abastecimentoDto.getMotoristaId())) {
            Motorista motorista = motoristaRepository.findById(abastecimentoDto.getMotoristaId())
                    .orElseThrow(() -> new RuntimeException("Motorista não encontrado!"));
            existingAbastecimento.setMotorista(motorista);
        }

        if (!existingAbastecimento.getVeiculo().getId().equals(abastecimentoDto.getVeiculoId())) {
            Veiculo veiculo = veiculoRepository.findById(abastecimentoDto.getVeiculoId())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
            existingAbastecimento.setVeiculo(veiculo);
        }

        if (!existingAbastecimento.getCombustivel().getId().equals(abastecimentoDto.getCombustivelId())) {
            Combustivel combustivel = combustivelRepository.findById(abastecimentoDto.getCombustivelId())
                    .orElseThrow(() -> new RuntimeException("Combustível não encontrado!"));
            existingAbastecimento.setCombustivel(combustivel);
        }

        Abastecimento updatedAbastecimento = abastecimentoRepository.save(existingAbastecimento);
        return convertToDto(updatedAbastecimento);
    }

    @Override
    @Transactional
    public void deleteAbastecimento(Long id) {
        if (!abastecimentoRepository.existsById(id)) {
            throw new RuntimeException("Abastecimento não encontrado!");
        }
        abastecimentoRepository.deleteById(id);
    }

    private AbastecimentoDto convertToDto(Abastecimento abastecimento) {
        AbastecimentoDto dto = modelMapper.map(abastecimento, AbastecimentoDto.class);
        dto.setPostoNome(abastecimento.getPosto().getNome());
        dto.setMotoristaNome(abastecimento.getMotorista().getNome());
        dto.setVeiculoPlaca(abastecimento.getVeiculo().getPlaca());
        dto.setCombustivelNome(abastecimento.getCombustivel().getNome());
        return dto;
    }
}