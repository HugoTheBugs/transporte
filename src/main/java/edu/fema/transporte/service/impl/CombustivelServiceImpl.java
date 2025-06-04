package edu.fema.transporte.service.impl;
import edu.fema.transporte.dto.CombustivelDto;
import edu.fema.transporte.entity.Combustivel;
import edu.fema.transporte.repository.CombustivelRepository;
import edu.fema.transporte.service.CombustivelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CombustivelServiceImpl implements CombustivelService {

    private CombustivelRepository combustivelRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CombustivelDto> getAllCombustiveis() {
        List<Combustivel> combustiveis = combustivelRepository.findAll();
        return combustiveis.stream().map((combustivel)->modelMapper.map(combustivel, CombustivelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void createCombustivel(CombustivelDto combustivelDto) {
        combustivelDto.setNome(combustivelDto.getNome().toUpperCase());
        Combustivel combustivel = modelMapper.map(combustivelDto, Combustivel.class);
        combustivelRepository.save(combustivel);
    }

    @Override
    public CombustivelDto getCombustivelById(Long id) {
        Combustivel combustivel = combustivelRepository.findById(id).get();
        CombustivelDto combustivelDto =  modelMapper.map(combustivel, CombustivelDto.class);
        return combustivelDto;
    }

    @Override
    public void updateCombustivel(CombustivelDto combustivelDto) {
        combustivelDto.setNome(combustivelDto.getNome().toUpperCase());
        combustivelRepository.save(modelMapper.map(combustivelDto, Combustivel.class));
    }

    @Override
    public void deleteCombustivel(Long id) {

        combustivelRepository.deleteById(id);
    }

}
