package ai.attus.testeattus.services;

import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService implements IPessoaService{

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public List<Pessoa> buscarPessoas() {
        List<Pessoa> all = pessoaRepository.findAll();
        return all;
    }
}
