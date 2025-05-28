package edu.fema.transporte.service;

import edu.fema.transporte.dto.ItinerarioDto;
import java.util.List;

public interface ItinerarioService {
    List<ItinerarioDto> getAllItinerarios();

    ItinerarioDto getItinerarioById(Long id) throws RuntimeException;

    List<ItinerarioDto> findByClienteId(Long clienteId);

    ItinerarioDto createItinerario(ItinerarioDto itinerarioDto) throws RuntimeException;

    ItinerarioDto updateItinerario(Long id, ItinerarioDto itinerarioDto) throws RuntimeException;

    void deleteItinerario(Long id) throws RuntimeException;
}