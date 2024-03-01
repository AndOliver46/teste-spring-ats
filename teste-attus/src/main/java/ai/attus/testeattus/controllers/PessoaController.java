package ai.attus.testeattus.controllers;

import ai.attus.testeattus.dtos.PessoaDTO;
import ai.attus.testeattus.dtos.PessoaEnderecoDTO;
import ai.attus.testeattus.services.IPessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<EntityModel<PessoaDTO>> criarPessoa(@RequestBody PessoaDTO pessoaDTO){
        PessoaDTO newPessoaDTO = pessoaService.criarPessoa(pessoaDTO);

        EntityModel<PessoaDTO> resource = EntityModel.of(newPessoaDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(newPessoaDTO.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).editarPessoa(newPessoaDTO, newPessoaDTO.getId())).withRel("editar"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EntityModel<PessoaDTO>> editarPessoa(@RequestBody PessoaDTO pessoaDTO, @PathVariable UUID id){
        PessoaDTO newPessoaDTO = pessoaService.editarPessoa(pessoaDTO, id);

        EntityModel<PessoaDTO> resource = EntityModel.of(newPessoaDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(newPessoaDTO.getId())).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PessoaEnderecoDTO>> buscarPessoa(@PathVariable UUID id){
        PessoaEnderecoDTO pessoaEnderecoDTO = pessoaService.buscarPessoa(id);

        EntityModel<PessoaEnderecoDTO> resource = EntityModel.of(pessoaEnderecoDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).editarPessoa(pessoaEnderecoDTO, pessoaEnderecoDTO.getId())).withRel("editar"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<PessoaDTO>>> buscarPessoas(Pageable pageable){
        List<PessoaDTO> pessoas = pessoaService.buscarPessoas(pageable);

        List<EntityModel<PessoaDTO>> resources = pessoas.stream().map(pessoa -> {
            EntityModel<PessoaDTO> resource = EntityModel.of(pessoa);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(pessoa.getId())).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).editarPessoa(pessoa, pessoa.getId())).withRel("editar"));
            return resource;
        }).toList();

        return ResponseEntity.ok(resources);
    }
}
