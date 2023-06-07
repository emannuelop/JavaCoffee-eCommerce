package br.unitins.ecommerce.service.usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.ecommerce.dto.endereco.EnderecoDTO;
import br.unitins.ecommerce.dto.telefone.TelefoneDTO;
import br.unitins.ecommerce.dto.usuario.PessoaFisicaDTO;
import br.unitins.ecommerce.dto.usuario.SenhaDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioBasicoDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioBasicoResponseDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisDTO;
import br.unitins.ecommerce.dto.usuario.listadesejo.ListaDesejoDTO;
import br.unitins.ecommerce.dto.usuario.listadesejo.ListaDesejoResponseDTO;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.produto.Produto;
import br.unitins.ecommerce.model.usuario.PessoaFisica;
import br.unitins.ecommerce.model.usuario.Sexo;
import br.unitins.ecommerce.model.usuario.Telefone;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.CafeRepository;
import br.unitins.ecommerce.repository.EnderecoRepository;
import br.unitins.ecommerce.repository.MunicipioRepository;
import br.unitins.ecommerce.repository.TelefoneRepository;
import br.unitins.ecommerce.repository.UsuarioRepository;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.service.pessoafisica.PessoaFisicaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioImplService implements UsuarioService {

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    CafeRepository cafeRepository;

    @Inject
    PessoaFisicaService pessoaFisicaService;

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
    public ListaDesejoResponseDTO getListaDesejo(Long id) throws NullPointerException {

        Usuario usuario = usuarioRepository.findById(id);

        if (usuario == null)
            throw new NullPointerException("usuario não encontrado");

        return new ListaDesejoResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO usuarioDto) throws ConstraintViolationException {

        validar(usuarioDto);

        Usuario entity = new Usuario();

        entity.setPessoaFisica(insertPessoaFisica(usuarioDto.pessoaFisicaDto()));

        entity.setLogin(usuarioDto.login());

        entity.setSenha(hashService.getHashSenha(usuarioDto.senha()));

        entity.setEndereco(insertEndereco(usuarioDto.endereco()));

        entity.setTelefonePrincipal(insertTelefone(usuarioDto.telefonePrincipal()));

        if (usuarioDto.telefoneOpcional() != null)
            entity.setTelefoneOpcional(insertTelefone(usuarioDto.telefoneOpcional()));

        usuarioRepository.persist(entity);

        return new UsuarioResponseDTO(entity);
    }

    @Override
    @Transactional
    public UsuarioBasicoResponseDTO insert(UsuarioBasicoDTO usuarioBasicoDto) throws ConstraintViolationException {

        validar(usuarioBasicoDto);

        Usuario entity = new Usuario();

        

        entity.setLogin(usuarioBasicoDto.login());

        entity.setSenha(hashService.getHashSenha(usuarioBasicoDto.senha()));

        usuarioRepository.persist(entity);

        return new UsuarioBasicoResponseDTO(entity);
    }

    @Override
    @Transactional
    public void insertListaDesejo(ListaDesejoDTO listaDto) throws NullPointerException {

        validar(listaDto);

        Usuario usuario = usuarioRepository.findById(listaDto.idUsuario());

        if (usuario == null)
            throw new NullPointerException("usuario não encontrado");

        usuario.setProdutos(cafeRepository.findById(listaDto.idProduto()));
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO usuarioDto)
            throws ConstraintViolationException, NotFoundException {

        validar(usuarioDto);

        Usuario entity = usuarioRepository.findById(id);

        if (entity == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        entity.setPessoaFisica(insertPessoaFisica(usuarioDto.pessoaFisicaDto()));

        entity.setLogin(usuarioDto.login());

        entity.setSenha(hashService.getHashSenha(usuarioDto.senha()));

        Long idEndereco = entity.getEndereco().getId();

        entity.setEndereco(insertEndereco(usuarioDto.endereco()));

        deleteEndereco(idEndereco);

        Long idTelefone = entity.getTelefonePrincipal().getId();

        entity.setTelefonePrincipal(insertTelefone(usuarioDto.telefonePrincipal()));

        deleteTelefone(idTelefone);

        if (usuarioDto.telefoneOpcional() != null && entity.getTelefoneOpcional() != null) {

            idTelefone = entity.getTelefoneOpcional().getId();

            entity.setTelefoneOpcional(insertTelefone(usuarioDto.telefoneOpcional()));

            deleteTelefone(idTelefone);
        }

        else if (usuarioDto.telefoneOpcional() != null && entity.getTelefoneOpcional() == null) {

            entity.setTelefoneOpcional(insertTelefone(usuarioDto.telefoneOpcional()));
        }

        else if (entity.getTelefoneOpcional() != null) {

            idTelefone = entity.getTelefoneOpcional().getId();

            entity.setTelefoneOpcional(null);

            deleteTelefone(idTelefone);
        }

        return new UsuarioResponseDTO(entity);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteProdutoFromListaDesejo(Long id, Long idProduto) {

        Usuario usuario = usuarioRepository.findById(id);

        if (usuario == null)
            return;

        usuario.getProdutos().remove(cafeRepository.findById(idProduto));
    }

    @Override
    @Transactional
    public void deleteProdutoFromListaDesejo(Produto produto) {

        List<Usuario> usuarios = usuarioRepository.findAll().list();

        for (Usuario usuario : usuarios) {

            if (usuario.getProdutos().contains(produto)) {

                deleteProdutoFromListaDesejo(usuario.getId(), produto.getId());
            }
        }
    }

    @Override
    public Long count() {

        return usuarioRepository.count();
    }

    @Override
    public Integer countListaDesejo(Long id) throws NullPointerException {

        Usuario usuario = usuarioRepository.findById(id);

        if (usuario == null)
            throw new NullPointerException("usuario não encontrado");

        if (usuario.getProdutos() == null)
            return null;

        return usuario.getProdutos().size();
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

    @Override
    public Usuario getByLogin(String login) {

        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario == null) {
            throw new NullPointerException("usuario não encontrado");
        }
        return usuario;
    }

    @Override
    public Usuario getByLoginAndSenha(String login, String senha) {

        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);

        return usuario;
    }

    @Override
    @Transactional
    public void update(Long id, DadosPessoaisDTO dadosPessoaisDTO) {
        
        validar(dadosPessoaisDTO);

        Usuario entity = usuarioRepository.findById(id);

        entity.getPessoaFisica().setEmail(dadosPessoaisDTO.email());

        entity.getPessoaFisica().setSexo(Sexo.valueOf(dadosPessoaisDTO.sexo()));
    }

    @Override
    @Transactional
    public void update(Long id, SenhaDTO senhaDTO) {
        
        validar(senhaDTO);

        Usuario entity = usuarioRepository.findById(id);

        if (entity.getSenha().equals(hashService.getHashSenha(senhaDTO.senhaAntiga())))
            entity.setSenha(hashService.getHashSenha(senhaDTO.senhaNova()));

        else
            throw new NotAuthorizedException("A senha inserida não corresponde à senha atual, acesso negado");
    }

    @Override
    @Transactional
    public void update(Long id, EnderecoDTO enderecoDTO) {
        
        validar(enderecoDTO);

        Usuario entity = usuarioRepository.findById(id);

        Long idEndereco = entity.getEndereco().getId();

        entity.setEndereco(insertEndereco(enderecoDTO));

        deleteEndereco(idEndereco);
    }

    @Override
    @Transactional
    public void update(Long id, String nomeImagem) {

        Usuario entity = usuarioRepository.findById(id);

        entity.setNomeImagem(nomeImagem);
    }

    @Override
    @Transactional
    public void updateTelefonePrincipal(Long id, TelefoneDTO telefonePrincipalDTO) {
        
        validar(telefonePrincipalDTO);

        Usuario entity = usuarioRepository.findById(id);

        Long idTelefone = entity.getTelefonePrincipal().getId();

        entity.setTelefonePrincipal(insertTelefone(telefonePrincipalDTO));

        deleteTelefone(idTelefone);
    }

    @Override
    @Transactional
    public void updateTelefoneOpcional(Long id, TelefoneDTO telefoneOpcionalDTO) {
        
        validar(telefoneOpcionalDTO);

        Long idTelefone;

        Usuario entity = usuarioRepository.findById(id);

        if (telefoneOpcionalDTO != null && entity.getTelefoneOpcional() != null) {

            idTelefone = entity.getTelefoneOpcional().getId();

            entity.setTelefoneOpcional(insertTelefone(telefoneOpcionalDTO));

            deleteTelefone(idTelefone);
        }

        else if (telefoneOpcionalDTO != null && entity.getTelefoneOpcional() == null) {

            entity.setTelefoneOpcional(insertTelefone(telefoneOpcionalDTO));
        }

        else if (entity.getTelefoneOpcional() != null) {

            idTelefone = entity.getTelefoneOpcional().getId();

            entity.setTelefoneOpcional(null);

            deleteTelefone(idTelefone);
        }
    }

    private PessoaFisica insertPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) throws ConstraintViolationException {

        return pessoaFisicaService.insertPessoaFisica(pessoaFisicaDTO);
    }

    private Telefone insertTelefone(TelefoneDTO telefoneDTO) throws ConstraintViolationException {

        validar(telefoneDTO);

        Telefone telefone = new Telefone();

        telefone.setCodigoArea(telefoneDTO.codigoArea());
        telefone.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(telefone);

        return telefone;
    }

    private void deleteTelefone(Long id) throws NotFoundException, IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Telefone telefone = telefoneRepository.findById(id);

        if (telefoneRepository.isPersistent(telefone))
            telefoneRepository.delete(telefone);

        else
            throw new NotFoundException("Nenhum Telefone encontrado");
    }

    private Endereco insertEndereco(EnderecoDTO enderecoDto) throws ConstraintViolationException {

        validar(enderecoDto);

        Endereco endereco = new Endereco();

        endereco.setLogradouro(enderecoDto.logradouro());

        endereco.setBairro(enderecoDto.bairro());

        endereco.setNumero(enderecoDto.numero());

        endereco.setComplemento(enderecoDto.complemento());

        endereco.setCep(enderecoDto.cep());

        endereco.setMunicipio(municipioRepository.findById(enderecoDto.idMunicipio()));

        enderecoRepository.persist(endereco);

        return endereco;
    }

    private void deleteEndereco(Long id) throws NotFoundException, IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Endereco endereco = enderecoRepository.findById(id);

        if (enderecoRepository.isPersistent(endereco))
            enderecoRepository.delete(endereco);

        else
            throw new NotFoundException("Nenhum endereço encontrado");
    }

    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(UsuarioBasicoDTO usuarioBasicoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<UsuarioBasicoDTO>> violations = validator.validate(usuarioBasicoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(TelefoneDTO telefoneDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<TelefoneDTO>> violations = validator.validate(telefoneDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(ListaDesejoDTO listaDto) throws ConstraintViolationException {

        Set<ConstraintViolation<ListaDesejoDTO>> violations = validator.validate(listaDto);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(DadosPessoaisDTO dadosPessoaisDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<DadosPessoaisDTO>> violations = validator.validate(dadosPessoaisDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    private void validar(SenhaDTO senhaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<SenhaDTO>> violations = validator.validate(senhaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
