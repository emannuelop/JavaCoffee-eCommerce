package br.unitins.ecommerce.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.ecommerce.dto.telefone.TelefoneDTO;
import br.unitins.ecommerce.dto.usuario.AuthUsuarioDTO;
import br.unitins.ecommerce.dto.usuario.SenhaDTO;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisDTO;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisResponseDTO;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.service.token.TokenJwtService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/perfil")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/auth")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUsuarioDTO authDTO) {
        
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.getByLoginAndSenha(authDTO.login(), hash);

        if (usuario == null) {
            return Response.status(Status.NO_CONTENT)
                            .entity("Usuario não encontrado").build();
        }

        return Response.ok()
                    .header("Authorization", tokenService.generateJwt(usuario))
                    .build();
    }

    @GET
    @Path("/dados_pessoais")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Response getDadosPessoais() {

        String login = jwt.getSubject();

        DadosPessoaisResponseDTO dadosPessoaisUsuario = new DadosPessoaisResponseDTO(usuarioService.getByLogin(login));

        return Response.ok(dadosPessoaisUsuario).build();
    }

    @PATCH
    @Path("/dados_pessoais")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Response updateDadosPessoais(DadosPessoaisDTO dadosPessoaisDTO) {

        String login = jwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        usuarioService.update(usuario.getId(), dadosPessoaisDTO);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/senha")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Response updateSenha(SenhaDTO senhaDTO) {

        String login = jwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        usuarioService.update(usuario.getId(), senhaDTO);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/telefone_principal")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Response updateTelefonePrincipal(TelefoneDTO telefonePrincipalDTO) {

        String login = jwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        usuarioService.updateTelefonePrincipal(usuario.getId(), telefonePrincipalDTO);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/telefone_opcional")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Response updateTelefoneOpcional(TelefoneDTO telefoneOpcionalDTO) {

        String login = jwt.getSubject();

        Usuario usuario = usuarioService.getByLogin(login);

        usuarioService.updateTelefoneOpcional(usuario.getId(), telefoneOpcionalDTO);

        return Response.status(Status.NO_CONTENT).build();
    }
}

