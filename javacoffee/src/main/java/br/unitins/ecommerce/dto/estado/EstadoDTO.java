package br.unitins.ecommerce.dto.estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EstadoDTO {

    @NotBlank(message = "Campo nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Campo sigla não pode estar vazio")
    @Size(max = 2, min = 2, message = "a sigla é composta por extamente duas letras")
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
