package edu.fema.transporte.service.impl;
import edu.fema.transporte.dto.MarcaDto;
import edu.fema.transporte.entity.Marca;
import edu.fema.transporte.repository.MarcaRepository;
import edu.fema.transporte.service.MarcaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MarcaServiceImpl implements MarcaService {
    
    private MarcaRepository marcaRepository;
    private ModelMapper modelMapper;

    @Override
    public List<MarcaDto> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas.stream().map((marca)->modelMapper.map(marca, MarcaDto.class)).collect(Collectors.toList());
    }

    @Override
    public void createMarca(MarcaDto marcaDto) {
        Marca marca = modelMapper.map(marcaDto, Marca.class);
        marcaRepository.save(marca);
    }

    @Override
    public MarcaDto getMarcaById(Long id) {
        Marca marca = marcaRepository.findById(id).get();
        MarcaDto marcaDto =  modelMapper.map(marca, MarcaDto.class);
        return marcaDto;
    }

    @Override
    public void updateMarca(MarcaDto marcaDto) {
        marcaRepository.save(modelMapper.map(marcaDto, Marca.class));
    }

    @Override
    public void deleteMarca(Long id) {

        marcaRepository.deleteById(id);
    }
    
}
