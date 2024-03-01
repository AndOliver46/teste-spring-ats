package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.dtos.PessoaEnderecoDTO;
import ai.attus.testeattus.models.Endereco;
import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PessoaServiceTests {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void quandoCriarPessoaDeveRetornarPessoaComID() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setDataNascimento(new Date());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(UUID.randomUUID());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO createdPessoaDTO = pessoaService.criarPessoa(pessoaDTO);

        assertNotNull(createdPessoaDTO);
        assertNotNull(createdPessoaDTO.getId());
        assertEquals(pessoaDTO.getNome(), createdPessoaDTO.getNome());
        assertEquals(pessoaDTO.getDataNascimento(), createdPessoaDTO.getDataNascimento());
    }

    @Test
    public void quandoEditarPessoaDeveRetornarPessoaAtualizada() {
        UUID pessoaId = UUID.randomUUID();
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João Editado");
        pessoaDTO.setDataNascimento(new Date());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        BeanUtils.copyProperties(pessoaDTO, pessoa);

        when(pessoaRepository.findById(any(UUID.class))).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaAtualizadaDTO = pessoaService.editarPessoa(pessoaDTO, pessoaId);

        assertNotNull(pessoaAtualizadaDTO);
        assertEquals(pessoaDTO.getNome(), pessoaAtualizadaDTO.getNome());
        assertEquals(pessoaDTO.getDataNascimento(), pessoaAtualizadaDTO.getDataNascimento());
    }

    @Test
    public void quandoBuscarPessoaEnderecoDeveRetornarPessoaComEnderecos() {
        UUID pessoaId = UUID.randomUUID();
        Set<EnderecoDTO> enderecoDTOs = new HashSet<>();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(UUID.randomUUID());
        enderecoDTO.setLogradouro("Rua Exemplo");
        enderecoDTO.setCep("12345-678");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTOs.add(enderecoDTO);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setEnderecos(enderecoDTOs.stream().map(enderecoDTO1 -> {
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(enderecoDTO1, endereco);
            return endereco;
        }).collect(Collectors.toSet()));

        when(pessoaRepository.findById(any(UUID.class))).thenReturn(Optional.of(pessoa));

        PessoaEnderecoDTO pessoaEnderecoEncontradoDTO = pessoaService.buscarPessoaEndereco(pessoaId);

        assertNotNull(pessoaEnderecoEncontradoDTO);
        assertEquals(pessoaId, pessoaEnderecoEncontradoDTO.getId());
        assertNotNull(pessoaEnderecoEncontradoDTO.getEnderecos());
        assertEquals(1, pessoaEnderecoEncontradoDTO.getEnderecos().size());
        EnderecoDTO foundEnderecoDTO = pessoaEnderecoEncontradoDTO.getEnderecos().iterator().next();
        assertEquals(enderecoDTO.getId(), foundEnderecoDTO.getId());
        assertEquals(enderecoDTO.getLogradouro(), foundEnderecoDTO.getLogradouro());
        assertEquals(enderecoDTO.getCep(), foundEnderecoDTO.getCep());
        assertEquals(enderecoDTO.getNumero(), foundEnderecoDTO.getNumero());
        assertEquals(enderecoDTO.getCidade(), foundEnderecoDTO.getCidade());
    }

    @Test
    public void quandoBuscarPessoaPorIdDeveRetornarPessoa() {
        UUID pessoaId = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("Maria");
        pessoa.setDataNascimento(new Date());

        when(pessoaRepository.findById(any(UUID.class))).thenReturn(Optional.of(pessoa));

        Pessoa pessoaEncontrada = pessoaService.buscarPessoa(pessoaId);

        assertNotNull(pessoaEncontrada);
        assertEquals(pessoaId, pessoaEncontrada.getId());
        assertEquals(pessoa.getNome(), pessoaEncontrada.getNome());
        assertEquals(pessoa.getDataNascimento(), pessoaEncontrada.getDataNascimento());
    }

    @Test
    public void quandoBuscarPessoasDeveRetornarPaginaDePessoas() {
        Page<Pessoa> pessoaPage = mock(Page.class);
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(UUID.randomUUID());
        pessoa1.setNome("João");
        pessoa1.setDataNascimento(new Date());
        pessoas.add(pessoa1);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(UUID.randomUUID());
        pessoa2.setNome("Maria");
        pessoa2.setDataNascimento(new Date());
        pessoas.add(pessoa2);

        when(pessoaPage.getContent()).thenReturn(pessoas);
        when(pessoaRepository.findAll(any(Pageable.class))).thenReturn(pessoaPage);

        List<PessoaDTO> pessoaDTOs = pessoaService.buscarPessoas(mock(Pageable.class));

        assertNotNull(pessoaDTOs);
        assertEquals(2, pessoaDTOs.size());

        PessoaDTO pessoaDTO1 = pessoaDTOs.get(0);
        assertNotNull(pessoaDTO1);
        assertEquals(pessoa1.getId(), pessoaDTO1.getId());
        assertEquals(pessoa1.getNome(), pessoaDTO1.getNome());
        assertEquals(pessoa1.getDataNascimento(), pessoaDTO1.getDataNascimento());

        PessoaDTO pessoaDTO2 = pessoaDTOs.get(1);
        assertNotNull(pessoaDTO2);
        assertEquals(pessoa2.getId(), pessoaDTO2.getId());
        assertEquals(pessoa2.getNome(), pessoaDTO2.getNome());
        assertEquals(pessoa2.getDataNascimento(), pessoaDTO2.getDataNascimento());
    }
}

