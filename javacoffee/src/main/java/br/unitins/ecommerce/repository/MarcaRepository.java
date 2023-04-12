package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.produto.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
}
