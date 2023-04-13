package br.unitins.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.produto.Marca;
import br.unitins.ecommerce.model.produto.cafe.Cafe;
import br.unitins.ecommerce.model.produto.cafe.Intensidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CafeRepository implements PanacheRepository<Cafe> {

    public List<Cafe> findByNome(String nome) {

        if (nome == null)
            return null;

        return find("FROM Cafe WHERE UPPER(UNACCENT(nome)) LIKE UNACCENT(?1)", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Cafe> findByIntensidade (Intensidade intensidade) {

        if (intensidade == null)
            return null;

        return find("FROM Cafe WHERE intensidade = ?1", intensidade).list();
    }

    public List<Cafe> findByMarca (Marca marca) {

        if (marca == null)
            return null;

        return find("FROM Cafe WHERE marca = ?1", marca).list();
    }

    public List<Cafe> filterByPrecoMaximo (Double preco) {

        if (preco == null)
            return null;

        return find("FROM Cafe WHERE preco < ?1", preco).list();
    }

    public List<Cafe> filterByPrecoMinimo (Double preco) {

        if (preco == null)
            return null;

        return find("FROM Cafe WHERE preco > ?1", preco).list();
    }

    public List<Cafe> filterByEntrePreco (Double precoMin, Double precoMax) {

        if (precoMin == null || precoMax == null)
            return null;

        return find("FROM Cafe WHERE preco > ?1 AND preco < ?2", precoMin, precoMax).list();
    }
}
