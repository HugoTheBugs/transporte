package edu.fema.transporte.service.impl;
import edu.fema.transporte.dto.MotoristaDto;
import edu.fema.transporte.entity.Motorista;
import edu.fema.transporte.repository.MotoristaRepository;
import edu.fema.transporte.service.MotoristaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MotoristaServiceImpl implements MotoristaService {

    private MotoristaRepository motoristaRepository;
    private ModelMapper modelMapper;

    @Override
    public List<MotoristaDto> getAllMotoristas() {
        List<Motorista> motoristas = motoristaRepository.findAll();
        return motoristas.stream().map((motorista)->modelMapper.map(motorista, MotoristaDto.class)).collect(Collectors.toList());
    }

    @Override
    public void createMotorista(MotoristaDto motoristaDto) {
        Motorista motorista = modelMapper.map(motoristaDto, Motorista.class);
        motoristaRepository.save(motorista);

    }

    @Override
    public MotoristaDto getMotoristaById(Long id) {
        Motorista motorista = motoristaRepository.findById(id).get();
        MotoristaDto motoristaDto = modelMapper.map(motorista, MotoristaDto.class);
        return motoristaDto;
    }

    @Override
    public void updateMotorista(MotoristaDto motoristaDto) {
        motoristaRepository.save(modelMapper.map(motoristaDto, Motorista.class));
    }

    @Override
    public void deleteMotorista(Long id) {
        motoristaRepository.deleteById(id);
    }
}
