package br.unitins.ecommerce.dto.cafe;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.ecommerce.model.produto.cafe.Cafe;
import br.unitins.ecommerce.model.produto.cafe.Intensidade;

public record CafeResponseDTO (
    Long id,
    String nome,
    String descricao,
    Double preco,
    String estoque,
    String nomeMarca,
    String modoPreparo,
    String tipo,
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Intensidade intensidade
) {

    public CafeResponseDTO(Cafe cafe) {

        this(cafe.getId(),
            cafe.getNome(),
            cafe.getDescricao(),
            cafe.getPreco(),
            cafe.getEstoque() > 0? "Disponível" : "Estoque esgotado",
            cafe.getMarca().getNome(),
            cafe.getMetodoDePreparo(),
            cafe.getTipo(),
            Intensidade.valueOf(cafe.getIntensidade().getId()));
    }
}
