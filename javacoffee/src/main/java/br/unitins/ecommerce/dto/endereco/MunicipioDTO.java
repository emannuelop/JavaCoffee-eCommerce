package br.unitins.ecommerce.dto.endereco;

import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public class MunicipioDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Long idestado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
    }

}
