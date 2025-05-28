package edu.fema.transporte.service;

import edu.fema.transporte.dto.PostoDto;

import java.util.List;

public interface PostoService {

    List<PostoDto> getAllPostos();

    void createPosto(PostoDto postoDto);

    PostoDto getPostoById(Long id);

    void updatePosto(PostoDto postoDto);

    void deletePosto(Long id);
}
