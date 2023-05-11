package br.unitins.ecommerce.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;
import br.unitins.ecommerce.service.cafe.CafeService;

@Path("/cafes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CafeResource {

    @Inject
    CafeService cafeService;

    @GET
    public List<CafeResponseDTO> getAll() {

        return cafeService.getAll();
    }

    @GET
    @Path("/{id}")
    public CafeResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {

        return cafeService.getById(id);
    }

    @POST
    public Response insert(CafeDTO cafeDto) {

        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(cafeService.insert(cafeDto))
                    .build();
        } catch (ConstraintViolationException e) {

            Result result = new Result(e.getConstraintViolations());

            return Response
                    .status(Status.NOT_FOUND)
                    .entity(result)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CafeDTO cafeDto) {

        try {

            cafeService.update(id, cafeDto);

            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {

            Result result = new Result(e.getConstraintViolations());

            return Response
                    .status(Status.NOT_FOUND)
                    .entity(result)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException {

        cafeService.delete(id);

        return Response
                .status(Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path("/count")
    public Long count() {

        return cafeService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    public List<CafeResponseDTO> getByNome(@PathParam("nome") String nome) {

        return cafeService.getByNome(nome);
    }

    @GET
    @Path("/searchByIntensidade/{intensidade}")
    public List<CafeResponseDTO> getByIntensidade(@PathParam("intensidade") Integer id) throws IndexOutOfBoundsException {

        return cafeService.getByIntensidade(id);
    }

    @GET
    @Path("/searchByMarca/{marca}")
    public List<CafeResponseDTO> getByMarca (@PathParam("marca") String nomeMarca) {

        return cafeService.getByMarca(nomeMarca);
    }

    @GET
    @Path("/filterByPrecoMin/{precoMin}")
    public List<CafeResponseDTO> filterByPrecoMin (@PathParam("precoMin") Double preco) {

        return cafeService.filterByPrecoMin(preco);
    }

    @GET
    @Path("/filterByPrecoMax/{precoMax}")
    public List<CafeResponseDTO> filterByPrecoMax (@PathParam("precoMax") Double preco) {

        return cafeService.filterByPrecoMax(preco);
    }

    @GET
    @Path("/filterByEntrePreco/{precoMin}/{precoMax}")
    public List<CafeResponseDTO> filterByEntrePreco (@PathParam("precoMin") Double precoMin, @PathParam("precoMax") Double precoMax) {

        return cafeService.filterByEntrePreco(precoMin, precoMax);
    }
}
