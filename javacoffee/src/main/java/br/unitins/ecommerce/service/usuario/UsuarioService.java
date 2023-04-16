package br.unitins.ecommerce.service.usuario;

import java.util.List;

import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {
    
    // Metodos basicos

    List<UsuarioResponseDTO> getAll();
    
    UsuarioResponseDTO getById(Long id);

    UsuarioResponseDTO insert(UsuarioDTO usuarioDto);

    UsuarioResponseDTO update(Long id, UsuarioDTO usuarioDto);

    void delete(Long id);

    // Metodos extras

    Long count();

    List<UsuarioResponseDTO> getByNome(String nome);
}
