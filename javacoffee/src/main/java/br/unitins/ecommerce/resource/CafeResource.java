package br.unitins.ecommerce.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;
import br.unitins.ecommerce.form.ImageForm;
import br.unitins.ecommerce.service.cafe.CafeService;
import br.unitins.ecommerce.service.file.FileService;

@Path("/cafes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CafeResource {

    @Inject
    CafeService cafeService;

    @Inject
    FileService fileService;

    @GET
    public List<CafeResponseDTO> getAll() {

        return cafeService.getAll();
    }

    @GET
    @Path("/{id}")
    public CafeResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {

        return cafeService.getById(id);
    }

    @GET
    @Path("/download/{nomeImagem}")
    @RolesAllowed({"Admin","User"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));

        response.header("Content-Disposition", "attachment;filename="+nomeImagem);

        return response.build();
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

    @PATCH
    @Path("/atualizar-imagem/{id}")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form, @PathParam("id") Long id){

        String nomeImagem = "";

        try {

            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.CONFLICT).entity(result).build();
        }

        cafeService.update(id, nomeImagem);

        return Response.status(Status.NO_CONTENT).build();
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
