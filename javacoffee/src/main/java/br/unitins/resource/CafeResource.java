package br.unitins.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unitins.model.produto.cafe.Cafe;
import br.unitins.repository.CafeRepository;

@Path("/cafes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CafeResource {
    
    @Inject
    CafeRepository cafeRepository;

    @GET
    public List<Cafe> getAll() {

        return cafeRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Cafe getById(@PathParam("id") Long id) {

        return cafeRepository.findById(id);
    }

    @POST
    @Transactional
    public Cafe insert(Cafe cafe) throws IllegalArgumentException {

        if (cafe == null)
            throw new IllegalArgumentException("Dados nulos");

        cafeRepository.persist(cafe);

        return cafe;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cafe update(@PathParam("id") Long id, Cafe cafe) throws IllegalArgumentException {

        Cafe entityCafe = cafeRepository.findById(id);

        if (entityCafe == null || cafe == null)
            throw new IllegalArgumentException("Dados nulos");

        entityCafe.setNome(cafe.getNome());

        entityCafe.setPreco(cafe.getPreco());

        entityCafe.setEstoque(cafe.getEstoque());

        entityCafe.setMarca(cafe.getMarca());

        entityCafe.setMetodoDePreparo(cafe.getMetodoDePreparo());

        entityCafe.setTipo(cafe.getTipo());

        entityCafe.setIntensidade(cafe.getIntensidade());

        return entityCafe;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) throws IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Cafe cafe = cafeRepository.findById(id);

        if (cafeRepository.isPersistent(cafe))
            cafeRepository.delete(cafe);
    }

    @GET
    @Path("/count")
    public Long count() {

        return cafeRepository.count();
    }
}
