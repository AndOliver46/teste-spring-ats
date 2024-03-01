package ai.attus.testeattus.dtos;

import ai.attus.testeattus.models.Endereco;

import java.util.HashSet;
import java.util.Set;

public class PessoaEnderecoDTO extends PessoaDTO {

    private Set<EnderecoDTO> enderecoDTOS = new HashSet<>();

    public PessoaEnderecoDTO() {
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecoDTOS;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecoDTOS) {
        this.enderecoDTOS = enderecoDTOS;
    }
}
