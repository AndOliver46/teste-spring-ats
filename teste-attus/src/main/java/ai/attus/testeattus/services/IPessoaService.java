package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IPessoaService {

    List<PessoaDTO> buscarPessoas();

    PessoaDTO criarPessoa(PessoaDTO pessoaDTO);

    PessoaDTO editarPessoa(PessoaDTO pessoaDTO, UUID id);

    PessoaDTO buscarPessoa(UUID id);
}
