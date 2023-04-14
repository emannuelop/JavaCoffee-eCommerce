package br.unitins.ecommerce.service.cafe;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;
import br.unitins.ecommerce.model.produto.cafe.Cafe;
import br.unitins.ecommerce.model.produto.cafe.Intensidade;
import br.unitins.ecommerce.repository.CafeRepository;
import br.unitins.ecommerce.repository.MarcaRepository;

@ApplicationScoped
public class CafeImplService implements CafeService {

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
    public void delete(Long id) throws IllegalArgumentException, NotFoundException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Cafe cafe = cafeRepository.findById(id);

        if (cafeRepository.isPersistent(cafe))
            cafeRepository.delete(cafe);

        throw new NotFoundException("Nenhum municipio encontrado");
    }

    @Override
    public Long count() {

        return cafeRepository.count();
    }

    @Override
    public List<CafeResponseDTO> getByNome(String nome) throws NullPointerException {

        List<Cafe> list = cafeRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum Café encontrado");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> getByIntensidade(Integer id) throws IndexOutOfBoundsException, NullPointerException {

        if (id < 0 || id > 3)
            throw new IndexOutOfBoundsException("número fora das opções");

        List<Cafe> list = cafeRepository.findByIntensidade(Intensidade.valueOf(id));

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> getByMarca(String nome) throws NullPointerException {

        List<Cafe> list = cafeRepository.findByMarca(marcaRepository.findByNome(nome).get(0));

        if (list == null)
            throw new NullPointerException("Nenhuma marca encontrada");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> filterByPrecoMin(Double preco) throws NullPointerException {
        
        List<Cafe> list = cafeRepository.filterByPrecoMinimo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> filterByPrecoMax(Double preco) {
        
        List<Cafe> list = cafeRepository.filterByPrecoMaximo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<CafeResponseDTO> filterByEntrePreco(Double precoMin, Double precoMax) {
        
        List<Cafe> list = cafeRepository.filterByEntrePreco(precoMin, precoMax);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(CafeResponseDTO::new)
                    .collect(Collectors.toList());
    }

    private void validar(CafeDTO cafeDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<CafeDTO>> violations = validator.validate(cafeDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
