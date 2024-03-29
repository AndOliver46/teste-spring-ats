package ai.attus.testeattus.dtos;

import java.util.Date;
import java.util.UUID;

public class PessoaDTO {

    private UUID id;
    private String nome;
    private Date dataNascimento;
    private UUID enderecoPrincipal;

    public PessoaDTO() {
    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public UUID getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(UUID enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }
}
