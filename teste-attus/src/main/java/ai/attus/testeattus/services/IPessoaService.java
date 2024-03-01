package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.models.Pessoa;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPessoaService {

    List<PessoaDTO> buscarPessoas();

    PessoaDTO criarPessoa(PessoaDTO pessoaDTO);
}
