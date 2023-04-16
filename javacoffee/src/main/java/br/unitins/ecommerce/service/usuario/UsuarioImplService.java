package br.unitins.ecommerce.service.usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.telefone.TelefoneDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.unitins.ecommerce.model.usuario.Telefone;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.EnderecoRepository;
import br.unitins.ecommerce.repository.TelefoneRepository;
import br.unitins.ecommerce.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioImplService implements UsuarioService {

    @Inject
    Validator validator;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Override
    public List<UsuarioResponseDTO> getAll() {
        
        return usuarioRepository.findAll()
                                    .stream()
                                    .map(UsuarioResponseDTO::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO getById(Long id) throws NotFoundException {
        
        Usuario usuario = usuarioRepository.findById(id);

        if (usuario == null)
            throw new NotFoundException("Não encontrado");

        return new UsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO insert(UsuarioDTO usuarioDto) throws ConstraintViolationException {
        
        validar(usuarioDto);

        Usuario entity = new Usuario();

        entity.setNome(usuarioDto.nome());

        entity.setEmail(usuarioDto.email());

        entity.setSenha(usuarioDto.senha());

        entity.setCpf(usuarioDto.cpf());

        entity.setEndereco(enderecoRepository.findById(usuarioDto.idEndereco()));

        entity.setTelefonePrincipal(insertTelefone(usuarioDto.telefonePrincipal()));

        if (usuarioDto.telefoneOpcional() != null)
            entity.setTelefoneOpcional(insertTelefone(usuarioDto.telefoneOpcional()));

        usuarioRepository.persist(entity);

        return new UsuarioResponseDTO(entity);
    }

    @Override
    public UsuarioResponseDTO update(Long id, UsuarioDTO usuarioDto) throws ConstraintViolationException, NotFoundException {
        
        validar(usuarioDto);

        Usuario entity = usuarioRepository.findById(id);

        if (entity == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        entity.setNome(usuarioDto.nome());

        entity.setEmail(usuarioDto.email());

        entity.setSenha(usuarioDto.senha());

        entity.setCpf(usuarioDto.cpf());

        entity.setEndereco(enderecoRepository.findById(usuarioDto.idEndereco()));

        entity.setTelefonePrincipal(insertTelefone(usuarioDto.telefonePrincipal()));

        if (usuarioDto.telefoneOpcional() != null)
            entity.setTelefoneOpcional(insertTelefone(usuarioDto.telefoneOpcional()));

        else if (entity.getTelefoneOpcional() != null)
            entity.setTelefoneOpcional(null);

        return new UsuarioResponseDTO(entity);
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException, NotFoundException {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Usuario usuario = usuarioRepository.findById(id);

        if (usuarioRepository.isPersistent(usuario))
            usuarioRepository.delete(usuario);

        else
            throw new NotFoundException("Nenhum usuario encontrado");
    }

    @Override
    public Long count() {
        
        return usuarioRepository.count();
    }

    @Override
    public List<UsuarioResponseDTO> getByNome(String nome) throws NullPointerException {
        
        List<Usuario> list = usuarioRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum usuario encontrado");

        return list.stream()
                    .map(UsuarioResponseDTO::new)
                    .collect(Collectors.toList());
    }

    private Telefone insertTelefone (TelefoneDTO telefoneDTO) {

        Telefone telefone = new Telefone();

        telefone.setCodigoArea(telefoneDTO.codigoArea());
        telefone.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(telefone);

        return telefone;
    }
    
    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
