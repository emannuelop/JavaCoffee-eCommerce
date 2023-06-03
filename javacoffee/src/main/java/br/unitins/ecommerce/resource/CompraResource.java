package br.unitins.ecommerce.resource;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.compra.CartaoCreditoDTO;
import br.unitins.ecommerce.dto.compra.ItemCompraDTO;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.compra.CompraService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/compras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompraResource {

    @Inject
    CompraService compraService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken tokenJwt;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @GET
    @Path("/historico-compras")
    @RolesAllowed({ "User" })
    public Response getAll() {

        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        try {

            return Response.ok(compraService.getAll(usuario.getId())).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/carrinho")
    @RolesAllowed({ "User" })
    public Response getCompraEmAndamento() {

        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        try {

            return Response.ok(compraService.getCompraEmAndamento(usuario.getId())).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @POST
    @Path("/carrinho/adicionar-item")
    @RolesAllowed({ "User" })
    public Response insertIntoCarrrinho(ItemCompraDTO itemCompraDTO) {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.insertItemIntoCompra(usuario.getId(), itemCompraDTO);

            return Response.status(Status.CREATED).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/remover-item/{idProduto}")
    @RolesAllowed({ "User" })
    public Response removeItemFromCarrinho(@PathParam("idProduto") Long idProduto) {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.removeItemCompra(usuario.getId(), idProduto);

            return Response.status(Status.NO_CONTENT).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/carrinho/cancelar-compra")
    @RolesAllowed({ "User" })
    public Response cancelarCompra() {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.cancelarCompra(usuario.getId());

            return Response.status(Status.NO_CONTENT).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-boleto-bancario")
    @RolesAllowed({ "User" })
    public Response pagarBoletoBancario() {

        // try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.efetuarPagamentoBoleto(usuario.getId());

            return Response.status(Status.ACCEPTED).build();
        // } catch (NullPointerException e) {

        //     Result result = new Result(e.getMessage(), false);

        //     return Response.status(Status.NOT_FOUND).entity(result).build();
        // }
    }

    @PATCH
    @Path("/carrinho/pagar-pix")
    @RolesAllowed({ "User" })
    public Response pagarPix() {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.efetuarPagamentoPix(usuario.getId());

            return Response.status(Status.ACCEPTED).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-cartao-credito")
    @RolesAllowed({ "User" })
    public Response pagarCartaoCredito(CartaoCreditoDTO cartaoCreditoDTO) {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            compraService.efetuarPagamentoCartaoCredito(usuario.getId(), cartaoCreditoDTO);

            return Response.status(Status.ACCEPTED).build();
        } catch (NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }
}
