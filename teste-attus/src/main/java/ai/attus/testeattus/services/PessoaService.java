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

    private PessoaRepository pessoaRepository;

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

        Pessoa pessoaAtualizada = pessoa.atualizarDados(pessoaDTO);
        pessoaRepository.save(pessoaAtualizada);

        PessoaDTO pessoaDTOAtualizada = new PessoaDTO();
        BeanUtils.copyProperties(pessoaAtualizada, pessoaDTOAtualizada);

        return pessoaDTOAtualizada;
    }

    @Override
    public PessoaEnderecoDTO buscarPessoa(UUID id) {

        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Excessao generica"));
        PessoaEnderecoDTO pessoaDTO = new PessoaEnderecoDTO();

        BeanUtils.copyProperties(pessoa, pessoaDTO);
        pessoaDTO.setEnderecos(pessoa.getEnderecos());

        return pessoaDTO;
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
