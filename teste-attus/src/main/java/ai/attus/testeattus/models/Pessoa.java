package ai.attus.testeattus.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Pessoa implements Serializable {

    private UUID id;
    private String nome;
    private UUID enderecoPrincipal;
    private Set<Endereco> enderecos = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(UUID enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", enderecoPrincipal=" + enderecoPrincipal +
                ", enderecos=" + enderecos +
                '}';
    }
}
