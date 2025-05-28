package edu.fema.transporte.repository;

import edu.fema.transporte.entity.Posto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostoRepository extends JpaRepository<Posto, Long> {
}
