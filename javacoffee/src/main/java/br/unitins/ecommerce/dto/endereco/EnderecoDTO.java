package br.unitins.ecommerce.dto.endereco;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoDTO {

    @NotBlank(message = "O campo logradouro não pode estar vazio")
    private String logradouro;

    @NotBlank(message = "O campo bairro não pode estar vazio")
    private String bairro;

    @NotBlank(message = "O campo número não pode estar vazio")
    private String numero;

    private String complemento;

    @NotBlank(message = "O campo cep não pode estar vazio")
    private String cep;

    @NotNull
    @Min(1)
    private Long idMunicipio;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
}
