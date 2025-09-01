package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

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
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class)))
    @GetMapping("/list")
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        try {
            List<Endereco> enderecos = enderecoService.listEndereco();
            return ResponseEntity.ok(enderecos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
        try {
            Endereco endereco = enderecoService.getById(id);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Criar novo endereço")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Endereco> createEndereco(
            @org.springframework.web.bind.annotation.RequestBody Endereco endereco) {
        try {
            Endereco novoEndereco = enderecoService.createEndereco(endereco);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Atualizar endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Endereco> updateEndereco(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody Endereco endereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.updateEndereco(id, endereco);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Excluir endereço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        try {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
