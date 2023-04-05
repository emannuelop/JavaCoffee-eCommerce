package br.unitins.model.pagamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unitins.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {

    @Column(nullable = false)
    private Boolean confirmacaoPagamento;

    private Date dataDeConfirmacaoPagamento;

    public Boolean getConfirmacaoPagamento() {
        return confirmacaoPagamento;
    }

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public Date getDataDeConfirmacaoPagamento() {
        return dataDeConfirmacaoPagamento;
    }

    public void setDataDeConfirmacaoPagamento(Date dataDeConfirmacaoPagamento) {
        this.dataDeConfirmacaoPagamento = dataDeConfirmacaoPagamento;
    }

}
