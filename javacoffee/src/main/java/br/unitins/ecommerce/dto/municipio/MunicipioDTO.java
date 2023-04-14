package br.unitins.ecommerce.dto.municipio;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MunicipioDTO {

    @NotBlank(message = "Campo nome não pode estar vazio")
    private String nome;

    @NotNull
    @Min(1)
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
