package br.unitins.ecommerce.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.CafeDTO;
import br.unitins.ecommerce.dto.CafeResponseDTO;
import br.unitins.ecommerce.model.produto.cafe.Cafe;
import br.unitins.ecommerce.model.produto.cafe.Intensidade;
import br.unitins.ecommerce.repository.CafeRepository;
import br.unitins.ecommerce.repository.MarcaRepository;

@ApplicationScoped
public class CafeServiceImpl implements CafeService {

    @Inject
    CafeRepository cafeRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Override
    public List<CafeResponseDTO> getAll() {

        return cafeRepository.findAll()
                .stream()
                .map(CafeResponseDTO::new)
                .toList();
    }

    @Override
    public CafeResponseDTO getById(Long id) throws NotFoundException {

        Cafe cafe = cafeRepository.findById(id);

        if (cafe == null)
            throw new NotFoundException("Não encontrado");

        return new CafeResponseDTO(cafe);
    }

    @Override
    public CafeResponseDTO insert(CafeDTO cafeDto) throws ConstraintViolationException {

        validar(cafeDto);

        Cafe entity = new Cafe();

        entity.setNome(cafeDto.getNome());

        entity.setDescricao(cafeDto.getDescricao());

        entity.setMarca(marcaRepository.findById(cafeDto.getIdMarca()));

        entity.setPreco(cafeDto.getPreco());

        entity.setEstoque(cafeDto.getEstoque());

        entity.setMetodoDePreparo(cafeDto.getModoPreparo());

        entity.setTipo(cafeDto.getTipo());

        entity.setIntensidade(Intensidade.valueOf(cafeDto.getIntensidade()));

        cafeRepository.persist(entity);

        return new CafeResponseDTO(entity);
    }

    @Override
    public CafeResponseDTO update(Long id, CafeDTO cafeDto) throws ConstraintViolationException {

        validar(cafeDto);

        Cafe entity = cafeRepository.findById(id);

        entity.setNome(cafeDto.getNome());

        entity.setDescricao(cafeDto.getDescricao());

        entity.setMarca(marcaRepository.findById(cafeDto.getIdMarca()));

        entity.setPreco(cafeDto.getPreco());

        entity.setEstoque(cafeDto.getEstoque());

        entity.setMetodoDePreparo(cafeDto.getModoPreparo());

        entity.setTipo(cafeDto.getTipo());

        entity.setIntensidade(Intensidade.valueOf(cafeDto.getIntensidade()));

        return new CafeResponseDTO(entity);
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Cafe cafe = cafeRepository.findById(id);

        if (cafeRepository.isPersistent(cafe))
            cafeRepository.delete(cafe);
    }

    @Override
    public Long count() {

        return cafeRepository.count();
    }

    @Override
    public List<CafeResponseDTO> getByNome(String nome) {

        List<Cafe> list = cafeRepository.findByNome(nome);

        return list.stream().map(CafeResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> getByIntensidade(Integer id) {

        List<Cafe> list = cafeRepository.findByIntensidade(id);

        return list.stream().map(CafeResponseDTO::new).collect(Collectors.toList());
    }

    private void validar(CafeDTO cafeDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<CafeDTO>> violations = validator.validate(cafeDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

}
