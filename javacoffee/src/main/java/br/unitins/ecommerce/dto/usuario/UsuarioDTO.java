package br.unitins.ecommerce.dto.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.unitins.ecommerce.dto.telefone.TelefoneDTO;

public record UsuarioDTO(

    @NotBlank(message = "O campo nome não pode estar nulo")
    String nome,

    @NotBlank(message = "O campo email não pode estar nulo")
    String email,

    @NotBlank(message = "O campo senha não pode estar nulo")
    String senha,

    @NotBlank(message = "O campo email não pode estar nulo")
    String cpf,

    @NotNull
    Long idEndereco,

    @NotNull
    TelefoneDTO telefonePrincipal,

    TelefoneDTO telefoneOpcional
) {
    
}
