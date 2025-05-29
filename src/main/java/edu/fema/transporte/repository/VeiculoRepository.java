package edu.fema.transporte.repository;

import edu.fema.transporte.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByMarcaId(Long marcaId);
    List<Veiculo> findByCombustivelId(Long combustivelId);
}
