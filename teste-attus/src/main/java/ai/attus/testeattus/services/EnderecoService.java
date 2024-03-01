package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.models.Endereco;
import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.EnderecoRepository;
import ai.attus.testeattus.services.interfaces.IEnderecoService;
import ai.attus.testeattus.services.interfaces.IPessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EnderecoService implements IEnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final IPessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, IPessoaService pessoaService) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
    }

    public EnderecoDTO criarEnderecoPessoa(EnderecoDTO enderecoDTO, UUID idPessoa) {

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);

        Pessoa pessoa = pessoaService.buscarPessoa(idPessoa);
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);

        pessoa.getEnderecos().add(endereco);
        pessoaService.salvarPessoa(pessoa);

        BeanUtils.copyProperties(endereco, enderecoDTO);
        return enderecoDTO;
    }
}