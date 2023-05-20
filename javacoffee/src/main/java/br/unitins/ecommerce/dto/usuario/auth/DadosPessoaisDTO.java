package br.unitins.ecommerce.dto.usuario.auth;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosPessoaisDTO(

    @NotBlank(message = "O campo email não pode estar nulo")
    String email,

    @NotNull
    @Min(1)
    Integer sexo
) {
    
}