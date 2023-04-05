package br.unitins.model.produto.cafe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.unitins.model.produto.Produto;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Herança de produto
public class Cafe extends Produto {

    @Column(nullable = false)
    private String metodoDePreparo;

    @Column(nullable = false)
    private String tipo;

    private Intensidade intensidade;

    public String getMetodoDePreparo() {
        return metodoDePreparo;
    }

    public void setMetodoDePreparo(String metodoDePreparo) {
        this.metodoDePreparo = metodoDePreparo;
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
