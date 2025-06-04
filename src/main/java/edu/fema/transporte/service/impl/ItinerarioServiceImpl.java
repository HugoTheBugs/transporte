package edu.fema.transporte.service.impl;

import edu.fema.transporte.dto.ItinerarioDto;
import edu.fema.transporte.entity.Cliente;
import edu.fema.transporte.entity.Itinerario;
import edu.fema.transporte.repository.ClienteRepository;
import edu.fema.transporte.repository.ItinerarioRepository;
import edu.fema.transporte.service.ItinerarioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItinerarioServiceImpl implements ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ItinerarioDto> getAllItinerarios() {
        return itinerarioRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItinerarioDto getItinerarioById(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerário não encontrado!"));
        return convertToDto(itinerario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItinerarioDto> findByClienteId(Long clienteId) {
        return itinerarioRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItinerarioDto createItinerario(ItinerarioDto itinerarioDto) {
        Cliente cliente = clienteRepository.findById(itinerarioDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        itinerarioDto.setDescricao(itinerarioDto.getDescricao().toUpperCase());
        Itinerario itinerario = modelMapper.map(itinerarioDto, Itinerario.class);
        itinerario.setCliente(cliente);

        Itinerario savedItinerario = itinerarioRepository.save(itinerario);
        return convertToDto(savedItinerario);
    }

    @Override
    @Transactional
    public ItinerarioDto updateItinerario(Long id, ItinerarioDto itinerarioDto) {

        Itinerario existingItinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerário não encontrado!"));

        existingItinerario.setDescricao(itinerarioDto.getDescricao().toUpperCase());

        if (!existingItinerario.getCliente().getId().equals(itinerarioDto.getClienteId())) {
            Cliente novoCliente = clienteRepository.findById(itinerarioDto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
            existingItinerario.setCliente(novoCliente);
        }

        Itinerario updatedItinerario = itinerarioRepository.save(existingItinerario);
        return convertToDto(updatedItinerario);
    }

    @Override
    @Transactional
    public void deleteItinerario(Long id) {
        if (!itinerarioRepository.existsById(id)) {
            throw new RuntimeException("Itinerário não encontrado!");
        }
        itinerarioRepository.deleteById(id);
    }

    private ItinerarioDto convertToDto(Itinerario itinerario) {
        ItinerarioDto dto = modelMapper.map(itinerario, ItinerarioDto.class);
        dto.setClienteNome(itinerario.getCliente().getNome());
        return dto;
    }
}