package ai.attus.testeattus.controllers;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.services.interfaces.IEnderecoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
