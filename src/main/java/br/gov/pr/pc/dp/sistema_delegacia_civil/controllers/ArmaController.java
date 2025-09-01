package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.ArmaService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/arma")
@Tag(name = "Arma")
public class ArmaController {

    private final ArmaService armaService;
    private final FileStorageService fileStorageService;

    @Operation(summary = "Listar todas as armas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arma.class)))
    @GetMapping("/list")
    public ResponseEntity<List<Arma>> getAllArmas() {
        try {
            return ResponseEntity.ok(armaService.listarTodasArmas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar arma por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arma encontrada",
                    content = @Content(schema = @Schema(implementation = Arma.class))),
            @ApiResponse(responseCode = "404", description = "Arma não encontrada")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<Arma> getArmaById(
            @Parameter(description = "ID da arma") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(armaService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Criar nova arma com imagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arma criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<Arma> createArma(
            @Parameter(description = "Dados da arma") @RequestPart("arma") Arma arma) {
        try {
            Arma novaArma = armaService.salvar(arma);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaArma);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Atualizar uma arma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arma atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Arma> updateArma(
            @Parameter(description = "ID da arma a ser atualizada") @PathVariable Long id,
            @Parameter(description = "Dados atualizados da arma") @RequestPart("arma") Arma arma) {
        try {
            Arma atualizada = armaService.atualizar(id, arma);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Excluir arma por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Arma deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Arma não encontrada")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArma(
            @Parameter(description = "ID da arma a ser deletada") @PathVariable Long id) {
        try {
            armaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
