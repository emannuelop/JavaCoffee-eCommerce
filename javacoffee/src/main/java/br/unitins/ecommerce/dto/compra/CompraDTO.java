package br.unitins.ecommerce.dto.compra;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompraDTO {

    private Double totalCompra;

    @NotBlank
    private String endereco;

    @NotNull
    private Long idPagamento;

    @NotNull
    private Long idUsuario;

    private List<Long> idItemCompra;

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getIdItemCompra() {
        return idItemCompra;
    }

    public void setIdItemCompra(List<Long> idItemCompra) {
        this.idItemCompra = idItemCompra;
    }

}
