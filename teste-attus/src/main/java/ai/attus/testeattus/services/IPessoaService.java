package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.dtos.PessoaEnderecoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IPessoaService {

    List<PessoaDTO> buscarPessoas(Pageable pageable);

    PessoaDTO criarPessoa(PessoaDTO pessoaDTO);

    PessoaDTO editarPessoa(PessoaDTO pessoaDTO, UUID id);

    PessoaEnderecoDTO buscarPessoa(UUID id);
}
