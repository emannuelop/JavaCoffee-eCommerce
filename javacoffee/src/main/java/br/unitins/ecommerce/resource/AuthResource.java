package br.unitins.ecommerce.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.ecommerce.dto.usuario.AuthUsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.unitins.ecommerce.dto.usuario.auth.UsuarioAuthDTO;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.UsuarioRepository;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.service.token.TokenJwtService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.Id;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioRepository usuarioRepository;

    @POST
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
    @Path("/perfil")
    @RolesAllowed({"Admin, User"})
    public Response getDadosPessoais() {

        String login = jwt.getSubject();
        UsuarioResponseDTO usuario = usuarioService.getByLogin(login);

        return Response.ok(usuario).build();
        
    }
}

