package br.unitins.ecommerce.service.endereco;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.endereco.EnderecoDTO;
import br.unitins.ecommerce.dto.endereco.EnderecoResponseDTO;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.repository.EnderecoRepository;
import br.unitins.ecommerce.repository.MunicipioRepository;

@ApplicationScoped
public class EnderecoImplService implements EnderecoService {

    @Inject
    Validator validator;

    @Inject
    EnderecoRepository enderecoRepository;
    
    @Inject
    MunicipioRepository municipioRepository;

    @Override
    public List<EnderecoResponseDTO> getAll() {
        
        return enderecoRepository.findAll()
                                    .stream()
                                    .map(EnderecoResponseDTO::new)
                                    .toList();
    }

    @Override
    public EnderecoResponseDTO getById(Long id) throws NotFoundException {
        
        Endereco endereco = enderecoRepository.findById(id);

        if (endereco == null)
            throw new NotFoundException("Não encontrado");

        return new EnderecoResponseDTO(endereco);
    }

    @Override
    public EnderecoResponseDTO insert(EnderecoDTO enderecoDto) throws ConstraintViolationException {
        
        validar(enderecoDto);

        Endereco entity = new Endereco();

        entity.setLogradouro(enderecoDto.logradouro());

        entity.setBairro(enderecoDto.bairro());

        entity.setNumero(enderecoDto.numero());

        entity.setComplemento(enderecoDto.complemento());

        entity.setCep(enderecoDto.cep());

        entity.setMunicipio(municipioRepository.findById(enderecoDto.idMunicipio()));

        enderecoRepository.persist(entity);

        return new EnderecoResponseDTO(entity);
    }

    @Override
    public EnderecoResponseDTO update(Long id, EnderecoDTO enderecoDto) throws ConstraintViolationException, NotFoundException {
        
        validar(enderecoDto);

        Endereco entity = enderecoRepository.findById(id);

        if (entity == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        entity.setLogradouro(enderecoDto.logradouro());

        entity.setBairro(enderecoDto.bairro());

        entity.setNumero(enderecoDto.numero());

        entity.setComplemento(enderecoDto.complemento());

        entity.setCep(enderecoDto.cep());

        entity.setMunicipio(municipioRepository.findById(enderecoDto.idMunicipio()));

        return new EnderecoResponseDTO(entity);
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException, NotFoundException {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Endereco endereco = enderecoRepository.findById(id);

        if (enderecoRepository.isPersistent(endereco))
            enderecoRepository.delete(endereco);

        else
            throw new NotFoundException("Nenhum endereco encontrado");
    }

    @Override
    public Long count() {
        
        return enderecoRepository.count();
    }

    @Override
    public List<EnderecoResponseDTO> getByLogradouro(String logradouro) throws NullPointerException {
        
        List<Endereco> list = enderecoRepository.findByLogradouro(logradouro);

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");

        return list.stream()
                    .map(EnderecoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<EnderecoResponseDTO> getByBairro(String bairro) {
        
        List<Endereco> list = enderecoRepository.findByBairro(bairro);

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");

        return list.stream()
                    .map(EnderecoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<EnderecoResponseDTO> getByCep(String cep) {
        
        List<Endereco> list = enderecoRepository.findByCep(cep);

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");

        return list.stream()
                    .map(EnderecoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<EnderecoResponseDTO> getByNomeMunicipio(String nomeMunicipio) {
        
        List<Endereco> list = enderecoRepository.findByMunicipio(municipioRepository.findByNome(nomeMunicipio).get(0));

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");

        return list.stream()
                    .map(EnderecoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
