package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.DelegaciaService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/delegacia")
@Tag(name = "Delegacia", description = "Operações relacionadas as delegacias cadastrados")
public class DelegaciaController {

    private final DelegaciaService delegaciaService;
    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;

    @Operation(summary = "Listar todas as delegacias")
    @ApiResponse(responseCode = "200", description = "Lista de delegacias retornada com sucesso")
    @GetMapping("/list")
    public ResponseEntity<List<Delegacia>> getAllDelegacias() {
        List<Delegacia> delegacias = delegaciaService.listAllDelegacias();
        return ResponseEntity.ok(delegacias);
    }

    @Operation(summary = "Buscar delegacia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delegacia encontrada"),
            @ApiResponse(responseCode = "404", description = "Delegacia não encontrada", content = @Content)
    })
    @GetMapping("list/{id}")
    public ResponseEntity<Delegacia> getDelegaciaById(
            @Parameter(description = "ID da delegacia") @PathVariable Long id) {
        try {
            Delegacia delegacia = delegaciaService.getById(id);
            return ResponseEntity.ok(delegacia);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Criar uma nova delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delegacia criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar delegacia", content = @Content)
    })
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Delegacia> createDelegacia(
            @RequestPart("delegacia") String delegaciaJson,
            @RequestParam("senha") String senha,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {
        try {
            Delegacia delegacia = objectMapper.readValue(delegaciaJson, Delegacia.class);

            Delegacia saved = delegaciaService.createDelegacia(delegacia, senha, imagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Atualizar uma delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delegacia atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar delegacia", content = @Content)
    })
    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"
    )
    public ResponseEntity<?> updateDelegacia(
            @PathVariable Long id,
            @RequestPart("delegacia") String delegaciaJson,
            @RequestParam(value = "senha", required = false) String senha,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {
        try {
            Delegacia delegacia = objectMapper.readValue(delegaciaJson, Delegacia.class);

            Delegacia atualizada = delegaciaService.updateDelegacia(id, delegacia, senha, imagem);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar delegacia: " + e.getMessage());
        }
    }

    @Operation(summary = "Deletar uma delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delegacia deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Delegacia não encontrada", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDelegacia(
            @Parameter(description = "ID da delegacia a ser deletada") @PathVariable Long id) {
        try {
            delegaciaService.deleteDelegacia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}