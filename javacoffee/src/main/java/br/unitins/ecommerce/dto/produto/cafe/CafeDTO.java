package br.unitins.ecommerce.dto.produto.cafe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CafeDTO {

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private Long idMarca;

    @NotNull
    @Min(1)
    private Double preco;

    @NotNull
    @Min(0)
    private Integer estoque;

    private String modoPreparo;
    
    private String tipo;

    @NotNull
    private Integer intensidade;

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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
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

    public Integer getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Integer intensidade) {
        this.intensidade = intensidade;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }
}
