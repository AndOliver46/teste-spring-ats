package ai.attus.testeattus.controllers;

import ai.attus.testeattus.dtos.EnderecoDTO;
import ai.attus.testeattus.services.interfaces.IEnderecoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).consultarEndereco(newEnderecoDTO.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).editarEndereco(newEnderecoDTO, newEnderecoDTO.getId())).withRel("editar"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EntityModel<EnderecoDTO>> editarEndereco(@RequestBody EnderecoDTO enderecoDTO, @PathVariable UUID id){
        EnderecoDTO newEnderecoDTO = enderecoService.editarEndereco(enderecoDTO, id);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).consultarEndereco(newEnderecoDTO.getId())).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EnderecoDTO>> consultarEndereco(@PathVariable UUID id){
        EnderecoDTO newEnderecoDTO = enderecoService.consultarEndereco(id);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).editarEndereco(newEnderecoDTO, newEnderecoDTO.getId())).withRel("editar"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).definirEnderecoPrincipal(newEnderecoDTO.getIdPessoa() ,newEnderecoDTO.getId())).withRel("definirPrincipal"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<EntityModel<EnderecoDTO>>> consultarEnderecosPessoa(@PathVariable UUID idPessoa){
        List<EnderecoDTO> enderecosList = enderecoService.consultarEnderecosPessoa(idPessoa);

        List<EntityModel<EnderecoDTO>> resources = enderecosList.stream().map(endereco -> {
            EntityModel<EnderecoDTO> resource = EntityModel.of(endereco);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).consultarEndereco(endereco.getId())).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).editarEndereco(endereco, endereco.getId())).withRel("editar"));
            return resource;
        }).toList();

        return ResponseEntity.ok(resources);
    }

    @PutMapping("/pessoa/{idPessoa}/enderecoPrincipal/{idEndereco}")
    public ResponseEntity<EntityModel<EnderecoDTO>> definirEnderecoPrincipal(@PathVariable UUID idPessoa, @PathVariable UUID idEndereco){
        EnderecoDTO newEnderecoDTO = enderecoService.definirEnderecoPrincipal(idPessoa, idEndereco);

        EntityModel<EnderecoDTO> resource = EntityModel.of(newEnderecoDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).consultarEndereco(newEnderecoDTO.getId())).withSelfRel());

        return ResponseEntity.ok(resource);
    }


}
