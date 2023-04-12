package br.unitins.ecommerce.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.produto.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
}
