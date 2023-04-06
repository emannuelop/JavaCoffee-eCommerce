package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.produto.cafe.Cafe;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CafeRepository implements PanacheRepository<Cafe> {
    
}
