package br.unitins.ecommerce.dto.endereco;

import javax.validation.constraints.NotBlank;

public class EstadoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
