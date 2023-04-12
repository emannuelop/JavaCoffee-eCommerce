package br.unitins.ecommerce.model.pagamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unitins.ecommerce.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {

    @Column(nullable = false)
    private Boolean confirmacaoPagamento;

    private Date dataConfirmacaoPagamento;

    public Boolean getConfirmacaoPagamento() {
        return confirmacaoPagamento;
    }

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public Date getDataDeConfirmacaoPagamento() {
        return dataConfirmacaoPagamento;
    }

    public void setDataDeConfirmacaoPagamento(Date dataDeConfirmacaoPagamento) {
        this.dataConfirmacaoPagamento = dataDeConfirmacaoPagamento;
    }

}
