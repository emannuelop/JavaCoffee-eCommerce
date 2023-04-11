package br.unitins.service;

import java.util.List;

import br.unitins.dto.CafeDTO;
import br.unitins.dto.CafeResponseDTO;

public interface CafeService {
    
    // Metodos basicos

    List<CafeResponseDTO> getAll();
    
    CafeResponseDTO getById(Long id);

    CafeResponseDTO insert(CafeDTO cafeDto);

    CafeResponseDTO update(Long id, CafeDTO cafeDto);

    void delete(Long id);

    // Metodos extras

    Long count();
}
