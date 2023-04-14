package br.unitins.ecommerce.dto.municipio;

import java.util.HashMap;
import java.util.Map;

import br.unitins.ecommerce.model.endereco.Municipio;

public class MunicipioResponseDTO {

    private Long id;
    private String nome;
    private Map<String, Object> estado;

    public MunicipioResponseDTO(Municipio municipio) {
        
        this.id = municipio.getId();
        this.nome = municipio.getNome();
        this.estado = new HashMap<>();

        estado.put("nome:", municipio.getEstado().getNome());
        estado.put("sigla:", municipio.getEstado().getSigla());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Map<String, Object> getEstado() {
        return estado;
    }
}
