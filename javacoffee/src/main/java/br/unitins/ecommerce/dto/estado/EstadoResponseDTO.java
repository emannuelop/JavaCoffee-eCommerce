package br.unitins.ecommerce.dto.estado;

import br.unitins.ecommerce.model.endereco.Estado;

public class EstadoResponseDTO {
    
    private Long id;
    private String nome;
    private String sigla;

    public EstadoResponseDTO (Estado estado) {

        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
