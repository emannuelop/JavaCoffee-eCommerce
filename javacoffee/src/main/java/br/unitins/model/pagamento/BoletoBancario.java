package br.unitins.model.pagamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class BoletoBancario extends Pagamento {

    @Column(nullable = false)
    private String codigoDeBarra;

    @Column(nullable = false)
    private Date dataDeGeracaoDoBoleto;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Date dataVencimento;

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public Date getDataDeGeracaoDoBoleto() {
        return dataDeGeracaoDoBoleto;
    }

    public void setDataDeGeracaoDoBoleto(Date dataDeGeracaoDoBoleto) {
        this.dataDeGeracaoDoBoleto = dataDeGeracaoDoBoleto;
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
