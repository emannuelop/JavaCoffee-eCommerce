package br.unitins.model.produto.cafeteira;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.unitins.model.produto.Produto;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cafeteira extends Produto {

    @Column(nullable = false)
    private String tipoDeCafeteira;

    @Column(nullable = false)
    private Voltagem voltagem;

    public String getTipoDeCafeteira() {
        return tipoDeCafeteira;
    }

    public void setTipoDeCafeteira(String tipoDeCafeteira) {
        this.tipoDeCafeteira = tipoDeCafeteira;
    }

    public Voltagem getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(Voltagem voltagem) {
        this.voltagem = voltagem;
    }

}
