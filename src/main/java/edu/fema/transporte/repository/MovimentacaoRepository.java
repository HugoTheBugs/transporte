package edu.fema.transporte.repository;
import edu.fema.transporte.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByVeiculoId(Long veiculoId);
    List<Movimentacao> findByMotoristaId(Long motoristaId);
    List<Movimentacao> findByItinerarioId(Long itinerario);
}
