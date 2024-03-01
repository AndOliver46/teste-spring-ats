package ai.attus.testeattus.services.interfaces;

import ai.attus.testeattus.dtos.EnderecoDTO;

import java.util.List;
import java.util.UUID;

public interface IEnderecoService {

    EnderecoDTO criarEnderecoPessoa(EnderecoDTO enderecoDTO, UUID idPessoa);

    EnderecoDTO editarEndereco(EnderecoDTO enderecoDTO, UUID id);

    EnderecoDTO consultarEndereco(UUID id);

    List<EnderecoDTO> consultarEnderecosPessoa(UUID idPessoa);

    EnderecoDTO definirEnderecoPrincipal(UUID idPessoa, UUID idEndereco);
}
