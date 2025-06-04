package edu.fema.transporte.service.impl;
import edu.fema.transporte.dto.VeiculoDto;
import edu.fema.transporte.entity.Combustivel;
import edu.fema.transporte.entity.Marca;
import edu.fema.transporte.entity.Veiculo;
import edu.fema.transporte.repository.CombustivelRepository;
import edu.fema.transporte.repository.MarcaRepository;
import edu.fema.transporte.repository.VeiculoRepository;
import edu.fema.transporte.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final MarcaRepository marcaRepository;
    private final CombustivelRepository combustivelRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public List<VeiculoDto> getAllVeiculos() {
        return veiculoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VeiculoDto getVeiculoById(Long id){
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
        return convertToDto(veiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VeiculoDto> findByMarcaId(Long marcaId) {
        return veiculoRepository.findByMarcaId(marcaId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VeiculoDto> findByCombustivelId(Long combustivelId) {
        return veiculoRepository.findByCombustivelId(combustivelId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VeiculoDto createVeiculo(VeiculoDto veiculoDto) {
        Marca marca = marcaRepository.findById(veiculoDto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        Combustivel combustivel = combustivelRepository.findById(veiculoDto.getCombustivelId())
                .orElseThrow(() -> new RuntimeException("Combustívell não encontrado"));
        veiculoDto.setPlaca(veiculoDto.getPlaca().toUpperCase());
        veiculoDto.setModelo(veiculoDto.getModelo().toUpperCase());
        Veiculo veiculo = modelMapper.map(veiculoDto, Veiculo.class);
        veiculo.setMarca(marca);
        veiculo.setCombustivel(combustivel);

        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
        return convertToDto(savedVeiculo);
    }

    @Override
    @Transactional
    public VeiculoDto updateVeiculo(Long id, VeiculoDto veiculoDto) {
        Veiculo existingVeiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        Marca marca = marcaRepository.findById(veiculoDto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada!"));
        Combustivel combustivel = combustivelRepository.findById(veiculoDto.getCombustivelId())
                .orElseThrow(() -> new RuntimeException("Combustível não encontrado!"));

        existingVeiculo.setPlaca(veiculoDto.getPlaca().toUpperCase());
        existingVeiculo.setModelo(veiculoDto.getModelo().toUpperCase());
        existingVeiculo.setAno(veiculoDto.getAno());
        existingVeiculo.setTanqueCapacidade(veiculoDto.getTanqueCapacidade());
        existingVeiculo.setCategoria(veiculoDto.getCategoria());
        existingVeiculo.setStatus(veiculoDto.getStatus());

        existingVeiculo.setMarca(marca);
        existingVeiculo.setCombustivel(combustivel);

        Veiculo updatedVeiculo = veiculoRepository.save(existingVeiculo);

        return convertToDto(updatedVeiculo);
    }

    @Override
    @Transactional
    public void deleteVeiculo(Long id) {
        if(!veiculoRepository.existsById(id)){
            throw new RuntimeException("Veículo não encontrado!");
        }
        veiculoRepository.deleteById(id);
    }

    private VeiculoDto convertToDto(Veiculo veiculo) {
        VeiculoDto dto = modelMapper.map(veiculo, VeiculoDto.class);
        dto.setMarcaNome(veiculo.getMarca().getNome());
        dto.setCombustivelNome(veiculo.getCombustivel().getNome());
        return dto;
    }
}
