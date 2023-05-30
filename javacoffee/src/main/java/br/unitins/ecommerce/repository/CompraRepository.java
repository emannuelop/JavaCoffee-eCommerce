package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.compra.Compra;
import br.unitins.ecommerce.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<Compra> {
    
    public Compra findByUsuario (Usuario usuario) {

        if (usuario == null)
            return null;

        return find("FROM Compra WHERE usuario = ?1", usuario).firstResult();
    }

    public Compra findByUsuarioWhereNotFinished (Usuario usuario) {

        if (usuario == null)
            return null;

        return find("FROM Compra WHERE usuario = ?1 AND ifConcluida = false", usuario).firstResult();
    }
}
