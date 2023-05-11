package br.unitins.ecommerce.model.produto.cafe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import br.unitins.ecommerce.model.produto.Produto;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Herança de produto
public class Cafe extends Produto {

    @Column(nullable = false)
    private String metodoPreparo;

    @Column(nullable = false)
    private String tipo;

    private Intensidade intensidade;

    public String getMetodoDePreparo() {
        return metodoPreparo;
    }

    public void setMetodoDePreparo(String metodoDePreparo) {
        this.metodoPreparo = metodoDePreparo;
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
