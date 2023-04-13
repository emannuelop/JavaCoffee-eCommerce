package br.unitins.ecommerce.dto.endereco;

import java.util.HashMap;
import java.util.Map;

import br.unitins.ecommerce.model.endereco.Municipio;

public class MunicipioResponseDTO {

    private String nome;

    private Map<String, Object> estado;

    public MunicipioResponseDTO(Municipio municipio) {
        this.nome = municipio.getNome();

        this.estado = new HashMap<>();

        estado.put("nome:", municipio.getEstado().getNome());
        estado.put("sigla:", municipio.getEstado().getSigla());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Object> getEstado() {
        return estado;
    }

    public void setEstado(Map<String, Object> estado) {
        this.estado = estado;
    }
}
