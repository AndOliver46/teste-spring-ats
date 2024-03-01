package ai.attus.testeattus.dtos;

import ai.attus.testeattus.models.Endereco;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PessoaEnderecoDTO extends PessoaDTO {

    private Set<Endereco> enderecos = new HashSet<>();

    public PessoaEnderecoDTO() {
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
