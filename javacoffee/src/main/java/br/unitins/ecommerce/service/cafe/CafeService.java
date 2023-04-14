package br.unitins.ecommerce.service.cafe;

import java.util.List;

import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;

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

    List<CafeResponseDTO> getByMarca(String nome);

    Long count();

        // metodos de filtragem

    List<CafeResponseDTO> filterByPrecoMin(Double preco);

    List<CafeResponseDTO> filterByPrecoMax(Double preco);

    List<CafeResponseDTO> filterByEntrePreco(Double precoMin, Double precoMax);
}