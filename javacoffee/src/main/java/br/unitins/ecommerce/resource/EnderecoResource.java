package br.unitins.ecommerce.resource;

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

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.endereco.EnderecoDTO;
import br.unitins.ecommerce.dto.endereco.EnderecoResponseDTO;
import br.unitins.ecommerce.service.endereco.EnderecoService;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {
    
    @Inject
    EnderecoService enderecoService;

    @GET
    public List<EnderecoResponseDTO> getAll() {

        return enderecoService.getAll();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {

        return enderecoService.getById(id);
    }

    @POST
    @Transactional
    public Response insert(EnderecoDTO enderecoDto) {

        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(enderecoService.insert(enderecoDto))
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
    public Response update(@PathParam("id") Long id, EnderecoDTO enderecoDto) {

        try {

            enderecoService.update(id, enderecoDto);

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
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {

        enderecoService.delete(id);

        return Response
                .status(Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path("/count")
    public Long count() {

        return enderecoService.count();
    }

    @GET
    @Path("/searchByLogradouro/{logradouro}")
    public List<EnderecoResponseDTO> getByLogradouro(@PathParam("logradouro") String logradouro) throws NullPointerException {

        return enderecoService.getByLogradouro(logradouro);
    }

    @GET
    @Path("/searchByBairro/{bairro}")
    public List<EnderecoResponseDTO> getByBairro(@PathParam("bairro") String bairro) throws NullPointerException {

        return enderecoService.getByBairro(bairro);
    }

    @GET
    @Path("/searchByCep/{cep}")
    public List<EnderecoResponseDTO> getByCep(@PathParam("cep") String cep) throws NullPointerException {

        return enderecoService.getByCep(cep);
    }

    @GET
    @Path("/searchByNomeMunicipio/{nomeMunicipio}")
    public List<EnderecoResponseDTO> getByNomeMunicipio(@PathParam("nomeMunicipio") String nomeMunicipio) throws NullPointerException {

        return enderecoService.getByNomeMunicipio(nomeMunicipio);
    }
}
