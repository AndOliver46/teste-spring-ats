package ai.attus.testeattus.controllers;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.services.IPessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private IPessoaService pessoaService;

    public PessoaController(IPessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO){
        PessoaDTO newPessoaDTO = pessoaService.criarPessoa(pessoaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> editarPessoa(@RequestBody PessoaDTO pessoaDTO, @PathVariable UUID id){
        PessoaDTO newPessoaDTO = pessoaService.editarPessoa(pessoaDTO, id);
        return ResponseEntity.ok(newPessoaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable UUID id){
        PessoaDTO pessoaDTO = pessoaService.buscarPessoa(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> buscarPessoas(){
        List<PessoaDTO> pessoas = pessoaService.buscarPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
