package edu.fema.transporte.service.impl;

import edu.fema.transporte.dto.ClienteDto;
import edu.fema.transporte.entity.Cliente;
import edu.fema.transporte.repository.ClienteRepository;
import edu.fema.transporte.service.ClienteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {


    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map((cliente)->modelMapper.map(cliente, ClienteDto.class)).collect(Collectors.toList());
    }

    @Override
    public void createCliente(ClienteDto clienteDto) {
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        clienteRepository.save(cliente);
    }

    @Override
    public ClienteDto getClienteById(Long id) {
        return null;
    }

    @Override
    public void updateCliente(ClienteDto clienteDto) {

    }
}
