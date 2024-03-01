package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IPessoaService {

    List<PessoaDTO> buscarPessoas(Pageable pageable);

    PessoaDTO criarPessoa(PessoaDTO pessoaDTO);

    PessoaDTO editarPessoa(PessoaDTO pessoaDTO, UUID id);

    PessoaDTO buscarPessoa(UUID id);
}
