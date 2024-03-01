package ai.attus.testeattus.controllers;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.services.interfaces.IEnderecoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/enderecos")
public class EnderecoController {

    private final IEnderecoService enderecoService;

    public EnderecoController(IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/novo/{idPessoa}")
    public ResponseEntity<EntityModel<EnderecoDTO>> criarEnderecoPessoa(@RequestBody EnderecoDTO enderecoDTO, @PathVariable UUID idPessoa){
        EnderecoDTO newEnderecoDTO = enderecoService.criarEnderecoPessoa(enderecoDTO, idPessoa);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        //resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(newEnderecoDTO.getId())).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EntityModel<EnderecoDTO>> editarEndereco(@RequestBody EnderecoDTO enderecoDTO, @PathVariable UUID id){
        EnderecoDTO newEnderecoDTO = enderecoService.editarEndereco(enderecoDTO, id);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        //resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(newEnderecoDTO.getId())).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EnderecoDTO>> consultarEndereco(@PathVariable UUID id){
        EnderecoDTO newEnderecoDTO = enderecoService.consultarEndereco(id);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        //resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(newEnderecoDTO.getId())).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<EntityModel<EnderecoDTO>>> consultarEnderecosPessoa(@PathVariable UUID idPessoa){
        List<EnderecoDTO> enderecosList = enderecoService.consultarEnderecosPessoa(idPessoa);

        List<EntityModel<EnderecoDTO>> resources = enderecosList.stream().map(endereco -> {
            EntityModel<EnderecoDTO> resource = EntityModel.of(endereco);
//            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).buscarPessoa(pessoa.getId())).withSelfRel());
//            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).editarPessoa(pessoa, pessoa.getId())).withRel("editar"));
            return resource;
        }).toList();

        return ResponseEntity.ok(resources);
    }


}
