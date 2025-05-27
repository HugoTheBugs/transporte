package edu.fema.transporte.service;
import edu.fema.transporte.dto.MotoristaDto;
import java.util.List;

public interface MotoristaService {

    List<MotoristaDto> getAllMotoristas();

    void createMotorista(MotoristaDto motoristaDto);

    MotoristaDto getMotoristaById(Long id);

    void updateMotorista(MotoristaDto motoristaDto);

    void deleteMotorista(Long id);
}
