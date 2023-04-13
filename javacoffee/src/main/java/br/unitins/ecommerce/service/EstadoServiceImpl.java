package br.unitins.ecommerce.service;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.endereco.EstadoDTO;
import br.unitins.ecommerce.model.endereco.Estado;
import br.unitins.ecommerce.repository.EstadoRepository;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<Estado> getAll() {

        return estadoRepository.findAll().list();
    }

    @Override
    public Estado getById(Long id) throws NotFoundException {

        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Não encontrado");

        return estado;
    }

    @Override
    public Estado insert(EstadoDTO estadoDto) throws ConstraintViolationException {

        validar(estadoDto);

        Estado entity = new Estado();

        entity.setNome(estadoDto.getNome());

        entity.setSigla(estadoDto.getSigla());

        estadoRepository.persist(entity);

        return entity;
    }

    @Override
    public Estado update(Long id, EstadoDTO estadoDto) throws ConstraintViolationException {

        validar(estadoDto);

        Estado entity = estadoRepository.findById(id);

        entity.setNome(estadoDto.getNome());

        entity.setSigla(estadoDto.getSigla());

        return entity;
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Estado estado = estadoRepository.findById(id);

        if (estadoRepository.isPersistent(estado))
            estadoRepository.delete(estado);
    }

    @Override
    public List<Estado> getByNome(String nome) throws NullPointerException {

        List<Estado> list = estadoRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum Café encontrado");

        return list;
    }

    @Override
    public Long count() {

        return estadoRepository.count();
    }

    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
