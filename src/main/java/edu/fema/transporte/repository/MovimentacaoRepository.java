package edu.fema.transporte.repository;
import edu.fema.transporte.entity.Abastecimento;
import edu.fema.transporte.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor<Movimentacao> {
    List<Movimentacao> findByVeiculoId(Long veiculoId);
    List<Movimentacao> findByMotoristaId(Long motoristaId);
    List<Movimentacao> findByItinerarioId(Long itinerario);
}
