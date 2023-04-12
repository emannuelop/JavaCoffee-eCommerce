package br.unitins.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.ecommerce.model.produto.cafe.Cafe;
import br.unitins.ecommerce.model.produto.cafe.Intensidade;

public class CafeResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String estoque;
    private String nomeMarca;
    private String modoPreparo;
    private String tipo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Intensidade intensidade;

    public CafeResponseDTO(Cafe cafe) {

        this.id = cafe.getId();
        this.nome = cafe.getNome();
        this.descricao = cafe.getDescricao();
        this.preco = cafe.getPreco();

        if (cafe.getEstoque() > 0)
            this.estoque = "Disponível";

        else
            this.estoque = "Estoque esgotado";

        this.nomeMarca = cafe.getMarca().getNome();
        this.modoPreparo = cafe.getMetodoDePreparo();
        this.tipo = cafe.getTipo();
        this.intensidade = Intensidade.valueOf(cafe.getIntensidade().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Intensidade getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Intensidade intensidade) {
        this.intensidade = intensidade;
    }
}
