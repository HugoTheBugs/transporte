package edu.fema.transporte.service.impl;

import edu.fema.transporte.dto.PostoDto;
import edu.fema.transporte.entity.Posto;
import edu.fema.transporte.repository.PostoRepository;
import edu.fema.transporte.service.PostoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostoServiceImpl implements PostoService {
    
    private PostoRepository postoRepository;
    private ModelMapper modelMapper;

    @Override
    public List<PostoDto> getAllPostos() {
        List<Posto> postos = postoRepository.findAll();
        return postos.stream().map((posto)->modelMapper.map(posto, PostoDto.class)).collect(Collectors.toList());
    }

    @Override
    public void createPosto(PostoDto postoDto) {
        Posto posto = modelMapper.map(postoDto, Posto.class);
        postoRepository.save(posto);

    }

    @Override
    public PostoDto getPostoById(Long id) {
        Posto posto = postoRepository.findById(id).get();
        PostoDto postoDto = modelMapper.map(posto, PostoDto.class);
        return postoDto;
    }

    @Override
    public void updatePosto(PostoDto postoDto) {
        postoRepository.save(modelMapper.map(postoDto, Posto.class));
    }

    @Override
    public void deletePosto(Long id) {
        postoRepository.deleteById(id);
    }
}
