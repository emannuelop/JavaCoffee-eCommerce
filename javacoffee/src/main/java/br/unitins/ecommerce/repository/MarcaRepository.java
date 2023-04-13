package br.unitins.ecommerce.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.produto.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
    public Marca findByNome (String nome) {

        if (nome == null)
            return null;

        return find("FROM Marca WHERE UPPER(UNACCENT(nome)) LIKE UNACCENT(?1)", "%" + nome.toUpperCase() + "%").firstResult();
    }
}
