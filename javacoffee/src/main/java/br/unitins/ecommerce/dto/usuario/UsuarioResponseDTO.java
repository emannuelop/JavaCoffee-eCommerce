package br.unitins.ecommerce.dto.usuario;

import java.util.HashMap;
import java.util.Map;

import br.unitins.ecommerce.dto.endereco.EnderecoResponseDTO;
import br.unitins.ecommerce.model.endereco.Municipio;
import br.unitins.ecommerce.model.usuario.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    String cpf,
    Map<String, Object> endereco,
    Map<String, Object> telefonePrincipal,
    Map<String, Object> telefoneOpcional
) {

    public UsuarioResponseDTO (Usuario usuario) {

        this(usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getCpf(),
            viewEndereco(usuario.getEndereco().getLogradouro(),
                        usuario.getEndereco().getBairro(), 
                        usuario.getEndereco().getNumero(), 
                        usuario.getEndereco().getComplemento(), 
                        usuario.getEndereco().getCep(),
                        usuario.getEndereco().getMunicipio()),
            viewTelefone(usuario.getTelefonePrincipal().getCodigoArea(), 
                        usuario.getTelefonePrincipal().getNumero()),
            usuario.getTelefoneOpcional() != null?
                                                viewTelefone(usuario.getTelefoneOpcional().getCodigoArea(),
                                                            usuario.getTelefoneOpcional().getNumero())
                                                : null);
    }
    
    private static Map<String, Object> viewEndereco (String logradouro, String bairro, String numero, String complemento, String cep, Municipio municipio) {

        Map<String, Object> endereco = new HashMap<>();

        endereco.put("Logradouro:", logradouro);
        endereco.put("Bairro:", bairro);
        endereco.put("Numero:", numero);
        endereco.put("Complemento:", complemento);
        endereco.put("Cep:", cep);
        endereco.put("Municipio:", EnderecoResponseDTO.viewMunicipio(municipio.getNome(), municipio.getEstado()));

        return endereco;
    }

    private static Map<String, Object> viewTelefone (String codigoArea, String numero) {

        Map<String, Object> telefone = new HashMap<>();

        telefone.put("Código de Área:", codigoArea);
        telefone.put("Número:", numero);

        return telefone;
    }
}
