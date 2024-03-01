package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.dtos.PessoaEnderecoDTO;
import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.PessoaRepository;
import ai.attus.testeattus.services.interfaces.IPessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PessoaService implements IPessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);
        pessoaRepository.save(pessoa);
        BeanUtils.copyProperties(pessoa, pessoaDTO);
        return pessoaDTO;
    }

    public PessoaDTO editarPessoa(PessoaDTO pessoaDTO, UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Excessao generica"));
        pessoa.atualizarDados(pessoaDTO);

        pessoaRepository.save(pessoa);

        PessoaDTO pessoaDTOAtualizada = new PessoaDTO();
        BeanUtils.copyProperties(pessoa, pessoaDTOAtualizada);

        return pessoaDTOAtualizada;
    }

    public PessoaEnderecoDTO buscarPessoaEndereco(UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Excessao generica"));
        PessoaEnderecoDTO pessoaDTO = new PessoaEnderecoDTO();

        BeanUtils.copyProperties(pessoa, pessoaDTO);
        pessoaDTO.setEnderecos(pessoa.getEnderecos());

        return pessoaDTO;
    }

    public Pessoa buscarPessoa(UUID id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Excessao generica"));
    }

    public void salvarPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public List<PessoaDTO> buscarPessoas(Pageable pageable) {
        Page<Pessoa> pessoaList = pessoaRepository.findAll(pageable);

        return pessoaList.getContent().stream().map(pessoa -> {
            PessoaDTO pessoaDTO = new PessoaDTO();
            BeanUtils.copyProperties(pessoa, pessoaDTO);
            return pessoaDTO;
        }).toList();
    }
}
