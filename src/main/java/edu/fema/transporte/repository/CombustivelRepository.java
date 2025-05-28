package edu.fema.transporte.repository;

import edu.fema.transporte.entity.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {
}
