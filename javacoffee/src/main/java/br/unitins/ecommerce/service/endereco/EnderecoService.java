package br.unitins.ecommerce.service.endereco;

import java.util.List;

import br.unitins.ecommerce.dto.endereco.EnderecoDTO;
import br.unitins.ecommerce.dto.endereco.EnderecoResponseDTO;

public interface EnderecoService {
    
    List<EnderecoResponseDTO> getAll();
    
    EnderecoResponseDTO getById(Long id);

    EnderecoResponseDTO insert(EnderecoDTO enderecoDto);

    EnderecoResponseDTO update(Long id, EnderecoDTO enderecoDto);

    void delete(Long id);

    // Metodos extras

    Long count();

    List<EnderecoResponseDTO> getByLogradouro(String logradouro);

    List<EnderecoResponseDTO> getByBairro(String bairro);

    List<EnderecoResponseDTO> getByCep(String cep);

    List<EnderecoResponseDTO> getByNomeMunicipio(String nomeMunicipio);
}
