package br.unitins.ecommerce.dto.endereco;

import java.util.HashMap;
import java.util.Map;

import br.unitins.ecommerce.dto.municipio.MunicipioResponseDTO;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.endereco.Estado;

public record EnderecoResponseDTO (
    Long id,
    String logradouro,
    String bairro,
    String numero,
    String complemento,
    String cep,
    Map<String, Object> municipio
) {
    
    public EnderecoResponseDTO(Endereco endereco) {

        this(endereco.getId(),
            endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep(),
            viewMunicipio(endereco.getMunicipio().getNome(), endereco.getMunicipio().getEstado()));
    }

    public static Map<String, Object> viewMunicipio(String nome, Estado estado) {

        Map<String, Object> municipio = new HashMap<>();

        municipio.put("nome:", nome);
        municipio.put("estado:", MunicipioResponseDTO.viewEstado(estado.getNome(), estado.getSigla()));

        return municipio;
    }
}
