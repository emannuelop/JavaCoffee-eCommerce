package br.unitins.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.produto.cafe.Cafe;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CafeRepository implements PanacheRepository<Cafe> {

    public List<Cafe> findByNome(String nome) {

        if (nome == null)
            return null;

        return find("FROM cafe WHERE UPPER(UNNACENT(nome)) LIKE UNNACENT(?1)", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Cafe> findByIntensidade (Integer id) throws IndexOutOfBoundsException {

        if (id == null)
            return null;

        if (id < 1 || id > 3)
            throw new IndexOutOfBoundsException("número fora das opções");

        return find("FROM cafe WHERE intensidade = ?1", id).list();
    }
}
