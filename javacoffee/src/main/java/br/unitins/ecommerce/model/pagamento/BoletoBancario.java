package br.unitins.model.pagamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class BoletoBancario extends Pagamento {

    @Column(nullable = false)
    private String codigoBarra;

    @Column(nullable = false)
    private Date dataGeracaoDoBoleto;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Date dataVencimento;

    public String getCodigoDeBarra() {
        return codigoBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoBarra = codigoDeBarra;
    }

    public Date getDataDeGeracaoDoBoleto() {
        return dataGeracaoDoBoleto;
    }

    public void setDataDeGeracaoDoBoleto(Date dataDeGeracaoDoBoleto) {
        this.dataGeracaoDoBoleto = dataDeGeracaoDoBoleto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

}