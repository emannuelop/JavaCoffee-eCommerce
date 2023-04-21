package br.unitins.ecommerce.dto.cafe;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CafeDTO(
    @NotBlank(message = "Campo nome não pode estar vazio")
    String nome,

    String descricao,

    @NotNull
    @Min(1)
    Long idMarca,

    @NotNull
    @Min(1)
    Double preco,

    @NotNull
    @Min(0)
    Integer estoque,

    String modoPreparo,
    String tipo,

    @NotNull
    @Min(1)
    @Max(3)
    Integer intensidade
) {

}
