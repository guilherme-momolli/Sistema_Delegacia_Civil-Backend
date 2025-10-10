package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Operation(summary = "Listar todos os endereços")
    @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDTO.class)))
    @GetMapping("/list")
    public ResponseEntity<List<EnderecoResponseDTO>> getAllEnderecos() {
        return ResponseEntity.ok(enderecoService.listEndereco());
    }

    @Operation(summary = "Buscar endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.getById(id));
    }

    @Operation(summary = "Criar novo endereço")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<EnderecoResponseDTO> createEndereco(
            @RequestBody EnderecoRequestDTO dto) {
        EnderecoResponseDTO novoEndereco = enderecoService.createEndereco(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @Operation(summary = "Atualizar endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(
            @PathVariable Long id,
            @RequestBody EnderecoRequestDTO dto) {
        EnderecoResponseDTO enderecoAtualizado = enderecoService.updateEndereco(id, dto);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @Operation(summary = "Excluir endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
