package edu.fema.transporte.service;

import edu.fema.transporte.dto.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> getAllClientes();

    void createCliente(ClienteDto clienteDto);

    ClienteDto getClienteById(Long id);

    void updateCliente(ClienteDto clienteDto);

    void deleteCliente(Long id);
}
