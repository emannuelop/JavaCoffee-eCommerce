package br.unitins.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unitins.application.Result;
import br.unitins.dto.CafeDTO;
import br.unitins.dto.CafeResponseDTO;
import br.unitins.service.CafeService;

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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Path("/search/{nome}")
    public List<CafeResponseDTO> getByNome(@PathParam("nome") String nome) {

        return cafeService.getByNome(nome);
    }
}
