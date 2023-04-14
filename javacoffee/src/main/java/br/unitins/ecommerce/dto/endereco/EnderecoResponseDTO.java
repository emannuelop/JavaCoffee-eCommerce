package br.unitins.ecommerce.dto.endereco;

import java.util.HashMap;
import java.util.Map;

import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;
import br.unitins.ecommerce.model.endereco.Endereco;

public class EnderecoResponseDTO {
    
    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String cep;
    private Map<String, Object> municipio;

    public EnderecoResponseDTO(Endereco endereco) {

        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
        this.municipio = new HashMap<>();

        municipio.put("nome:", endereco.getMunicipio().getNome());
        municipio.put("estado:", new EstadoResponseDTO(endereco.getMunicipio().getEstado()));
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public Map<String, Object> getMunicipio() {
        return municipio;
    }
}
