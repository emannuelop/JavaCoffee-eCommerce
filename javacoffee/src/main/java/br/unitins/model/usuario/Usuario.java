package br.unitins.model.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.unitins.model.DefaultEntity;
import br.unitins.model.endereco.Endereco;
import br.unitins.model.pagamento.Pagamento;
import br.unitins.model.produto.Produto;

@Entity
public class Usuario extends DefaultEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToMany
    @JoinTable(name = "lista_desejo",
                joinColumns = @JoinColumn(name = "id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "id_produto"))
    // Criando uma tabela auxiliar
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name = "id_usuario", nullable = false)
    private List<Pagamento> pagamento;

    @OneToOne
    @JoinColumn(name = "id_telefone_principal", unique = true, nullable = false)
    private Telefone telefonePrincipal;

    @OneToMany
    @JoinColumn(name = "id_usuario_telefone_opcional", nullable = false)
    private List<Telefone> telefonesOpcionais;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pagamento> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public Telefone getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(Telefone telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public List<Telefone> getTelefonesOpcionais() {
        return telefonesOpcionais;
    }

    public void setTelefonesOpcionais(List<Telefone> telefonesOpcionais) {
        this.telefonesOpcionais = telefonesOpcionais;
    }

}
