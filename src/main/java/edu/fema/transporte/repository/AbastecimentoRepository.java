package edu.fema.transporte.repository;
import edu.fema.transporte.entity.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long>, JpaSpecificationExecutor<Abastecimento> {

        List<Abastecimento> findByPostoId (Long postoId);
        List<Abastecimento> findByMotoristaId (Long motoristaId);
        List<Abastecimento> findByVeiculoId (Long veiculoId);
        List<Abastecimento> findByCombustivelId (Long combustivelId);

}