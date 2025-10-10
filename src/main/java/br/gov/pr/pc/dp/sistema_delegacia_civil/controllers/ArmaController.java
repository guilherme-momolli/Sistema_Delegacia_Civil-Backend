package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.ArmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/armas")
@Tag(name = "Armas", description = "Operações relacionadas às armas vinculadas aos bens")
public class ArmaController {

    private final ArmaService armaService;

    @Operation(summary = "Cadastrar uma nova arma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arma criada com sucesso",
                    content = @Content(schema = @Schema(implementation = ArmaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ArmaResponseDTO> create(@Valid @RequestBody ArmaRequestDTO dto) {
        ArmaResponseDTO response = armaService.create(dto);
        return ResponseEntity.created(URI.create("/armas/" + response.getBemId())).body(response);
    }

    @Operation(summary = "Listar todas as armas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = ArmaResponseDTO.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<ArmaResponseDTO>> listAll() {
        return ResponseEntity.ok(armaService.listAll());
    }

    @Operation(summary = "Buscar arma por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arma encontrada",
                    content = @Content(schema = @Schema(implementation = ArmaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Arma não encontrada", content = @Content)
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<ArmaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(armaService.getById(id));
    }

    @Operation(summary = "Atualizar dados de uma arma existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arma atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = ArmaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Arma não encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ArmaResponseDTO> update(@PathVariable Long id,
                                                  @Valid @RequestBody ArmaRequestDTO dto) {
        ArmaResponseDTO response = armaService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir uma arma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Arma excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Arma não encontrada", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        armaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}