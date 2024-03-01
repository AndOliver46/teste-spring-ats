package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService implements IPessoaService{

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);
        pessoaRepository.save(pessoa);
        return pessoaDTO;
    }

    @Override
    public PessoaDTO editarPessoa(PessoaDTO pessoaDTO, UUID id) {

        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Excessao generica"));

        Pessoa pessoaAtualizada = pessoa.atualizarDados(pessoaDTO);
        pessoaRepository.save(pessoaAtualizada);

        PessoaDTO pessoaDTOAtualizada = new PessoaDTO();
        BeanUtils.copyProperties(pessoaAtualizada, pessoaDTOAtualizada);

        return pessoaDTOAtualizada;
    }

    @Transactional
    public List<PessoaDTO> buscarPessoas() {
        List<Pessoa> pessoaList = pessoaRepository.findAll();

        return pessoaList.stream().map(pessoa -> {
            PessoaDTO pessoaDTO = new PessoaDTO();
            BeanUtils.copyProperties(pessoa, pessoaDTO);
            return pessoaDTO;
        }).toList();
    }
}
