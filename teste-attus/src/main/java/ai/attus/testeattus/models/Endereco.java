package ai.attus.testeattus.models;

import ai.attus.testeattus.enums.Estado;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Endereco implements Serializable {

    private UUID endereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Estado estado;

    public UUID getEndereco() {
        return endereco;
    }

    public void setEndereco(UUID endereco) {
        this.endereco = endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(cep, endereco.cep) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade) && estado == endereco.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, cep, numero, cidade, estado);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "endereco=" + endereco +
                ", logradouro='" + logradouro + '\'' +
                ", cep='" + cep + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado=" + estado +
                '}';
    }
}
