package ai.attus.testeattus.controllers;

import ai.attus.testeattus.models.Pessoa;
import ai.attus.testeattus.services.IPessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private IPessoaService pessoaService;

    public PessoaController(IPessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarPessoas(){
        List<Pessoa> pessoas = pessoaService.buscarPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
