package edu.fema.transporte.repository;

import edu.fema.transporte.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    List<Itinerario> findByClienteId(Long clienteId);
}
