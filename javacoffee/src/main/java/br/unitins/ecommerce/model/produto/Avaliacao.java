package br.unitins.ecommerce.model.produto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.usuario.Usuario;

@Entity
public class Avaliacao extends DefaultEntity {

    private String comentario;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Estrela estrela;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true) // Quando é OneToOne precisa colocar unique pois o usuario não pode
                                                    // se repetir
    private Usuario usuario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Estrela getEstrela() {
        return estrela;
    }

    public void setEstrela(Estrela estrela) {
        this.estrela = estrela;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
