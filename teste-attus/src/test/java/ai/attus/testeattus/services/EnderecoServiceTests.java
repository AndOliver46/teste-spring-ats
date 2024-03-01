package ai.attus.testeattus.services;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.enums.Estado;
import ai.attus.testeattus.models.Endereco;
import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.repositories.EnderecoRepository;
import ai.attus.testeattus.services.exceptions.EnderecoNaoPertenceException;
import ai.attus.testeattus.services.interfaces.IPessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnderecoServiceTests {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private IPessoaService pessoaService;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void quandoCriarEnderecoDeveRetornarEnderecoComID() {
        //Given - Dado

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua Exemplo");
        enderecoDTO.setCep("12345-678");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado(Estado.SP);
        enderecoDTO.setIdPessoa(UUID.randomUUID());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(enderecoDTO.getIdPessoa());

        //When - Quando

        when(pessoaService.buscarPessoa(any(UUID.class))).thenReturn(pessoa);
        when(enderecoRepository.save(any(Endereco.class))).thenAnswer(invocation -> {
            Endereco endereco = invocation.getArgument(0);
            endereco.setId(UUID.randomUUID());
            return endereco;
        });

        EnderecoDTO createdEnderecoDTO = enderecoService.criarEnderecoPessoa(enderecoDTO, enderecoDTO.getIdPessoa());

        // Then - Entao

        assertNotNull(createdEnderecoDTO);
        assertNotNull(createdEnderecoDTO.getId());
        assertEquals(enderecoDTO.getLogradouro(), createdEnderecoDTO.getLogradouro());
        assertEquals(enderecoDTO.getCep(), createdEnderecoDTO.getCep());
        assertEquals(enderecoDTO.getNumero(), createdEnderecoDTO.getNumero());
        assertEquals(enderecoDTO.getCidade(), createdEnderecoDTO.getCidade());
        assertEquals(enderecoDTO.getEstado(), createdEnderecoDTO.getEstado());
    }

    @Test
    public void quandoEditarEnderecoDeveRetornarEnderecoEditado() {
        UUID enderecoId = UUID.randomUUID();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua Exemplo");
        enderecoDTO.setCep("12345-678");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado(Estado.SP);

        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);

        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EnderecoDTO updatedEnderecoDTO = enderecoService.editarEndereco(enderecoDTO, enderecoId);

        assertNotNull(updatedEnderecoDTO);
        assertEquals(enderecoDTO.getLogradouro(), updatedEnderecoDTO.getLogradouro());
        assertEquals(enderecoDTO.getCep(), updatedEnderecoDTO.getCep());
        assertEquals(enderecoDTO.getNumero(), updatedEnderecoDTO.getNumero());
        assertEquals(enderecoDTO.getCidade(), updatedEnderecoDTO.getCidade());
        assertEquals(enderecoDTO.getEstado(), updatedEnderecoDTO.getEstado());
    }

    @Test
    public void quandoConsultarEnderecoPorIdDeveRetornarEnderecoCompleto() {
        UUID enderecoId = UUID.randomUUID();
        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);
        endereco.setLogradouro("Rua Exemplo");
        endereco.setCep("12345-678");
        endereco.setNumero("123");
        endereco.setCidade("São Paulo");
        endereco.setEstado(Estado.SP);

        UUID pessoaId = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("João Editado");
        pessoa.setDataNascimento(new Date());
        endereco.setPessoa(pessoa);

        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.of(endereco));

        EnderecoDTO foundEnderecoDTO = enderecoService.consultarEndereco(enderecoId);

        assertNotNull(foundEnderecoDTO);
        assertEquals(enderecoId, foundEnderecoDTO.getId());
        assertEquals(endereco.getLogradouro(), foundEnderecoDTO.getLogradouro());
        assertEquals(endereco.getCep(), foundEnderecoDTO.getCep());
        assertEquals(endereco.getNumero(), foundEnderecoDTO.getNumero());
        assertEquals(endereco.getCidade(), foundEnderecoDTO.getCidade());
        assertEquals(endereco.getEstado(), foundEnderecoDTO.getEstado());
    }

    @Test
    public void quandoConsultarEnderecosDaPessoaDeveRetornarTodosEnderecos() {
        UUID pessoaId = UUID.randomUUID();
        Set<Endereco> enderecos = new HashSet<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(UUID.randomUUID());
        endereco1.setLogradouro("Rua Exemplo 1");
        endereco1.setCep("12345-678");
        endereco1.setNumero("123");
        endereco1.setCidade("São Paulo");
        endereco1.setEstado(Estado.SP);
        enderecos.add(endereco1);
        Endereco endereco2 = new Endereco();
        endereco2.setId(UUID.randomUUID());
        endereco2.setLogradouro("Rua Exemplo 2");
        endereco2.setCep("98765-432");
        endereco2.setNumero("456");
        endereco2.setCidade("Rio de Janeiro");
        endereco2.setEstado(Estado.RJ);
        enderecos.add(endereco2);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setEnderecos(enderecos);

        when(pessoaService.buscarPessoa(any(UUID.class))).thenReturn(pessoa);

        List<EnderecoDTO> enderecoDTOs = enderecoService.consultarEnderecosPessoa(pessoaId);

        assertNotNull(enderecoDTOs);
        assertEquals(2, enderecoDTOs.size());
        assertTrue(enderecoDTOs.stream().anyMatch(enderecoDTO -> enderecoDTO.getId().compareTo(endereco1.getId()) == 0));
        assertTrue(enderecoDTOs.stream().anyMatch(enderecoDTO -> enderecoDTO.getId().compareTo(endereco2.getId()) == 0));
    }

    @Test
    public void quandoDefinirEnderecoPadraoCorretamenteDeveRetornarEnderecoDTOComId() {
        UUID enderecoId = UUID.randomUUID();
        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);

        UUID pessoaId = UUID.randomUUID();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.getEnderecos().add(endereco);

        endereco.setPessoa(pessoa);

        when(pessoaService.buscarPessoa(any(UUID.class))).thenReturn(pessoa);
        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.of(endereco));

        EnderecoDTO principalEnderecoDTO = enderecoService.definirEnderecoPrincipal(pessoaId, enderecoId);

        assertNotNull(principalEnderecoDTO);
        assertEquals(enderecoId, principalEnderecoDTO.getId());
    }

    @Test
    public void quandoDefinirEnderecoPadraoIncorretamenteDeveLancarExcessao() {
        UUID pessoaId = UUID.randomUUID();
        UUID enderecoId = UUID.randomUUID();
        Endereco endereco = new Endereco();
        endereco.setId(UUID.randomUUID());
        endereco.setPessoa(new Pessoa());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.getEnderecos().add(endereco);

        when(pessoaService.buscarPessoa(any(UUID.class))).thenReturn(pessoa);

        assertThrows(EnderecoNaoPertenceException.class, () -> {
            enderecoService.definirEnderecoPrincipal(pessoaId, enderecoId);
        });
    }
}
