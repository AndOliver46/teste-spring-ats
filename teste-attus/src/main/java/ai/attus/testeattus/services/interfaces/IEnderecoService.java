package ai.attus.testeattus.services.interfaces;

import ai.attus.testeattus.dtos.EnderecoDTO;

import java.util.UUID;

public interface IEnderecoService {

    EnderecoDTO criarEnderecoPessoa(EnderecoDTO enderecoDTO, UUID idPessoa);

}
