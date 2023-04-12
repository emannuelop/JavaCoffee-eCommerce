package br.unitins.ecommerce.service;

import java.util.List;

import br.unitins.ecommerce.dto.CafeDTO;
import br.unitins.ecommerce.dto.CafeResponseDTO;

public interface CafeService {
    
    // Metodos basicos

    List<CafeResponseDTO> getAll();
    
    CafeResponseDTO getById(Long id);

    CafeResponseDTO insert(CafeDTO cafeDto);

    CafeResponseDTO update(Long id, CafeDTO cafeDto);

    void delete(Long id);

    // Metodos extras

    List<CafeResponseDTO> getByNome(String nome);

    List<CafeResponseDTO> getByIntensidade(Integer id);

    Long count();
}
