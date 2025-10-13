package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.DelegaciaService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/delegacia")
@Tag(name = "Delegacia", description = "Operações relacionadas às delegacias cadastradas")
public class DelegaciaController {

    private final DelegaciaService delegaciaService;
    private final ObjectMapper objectMapper;

    @Operation(summary = "Listar todas as delegacias")
    @GetMapping("/list")
    public ResponseEntity<List<DelegaciaResponseDTO>> listarDelegacias() {
        return ResponseEntity.ok(delegaciaService.listAll());
    }

    @Operation(summary = "Buscar delegacia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delegacia encontrada"),
            @ApiResponse(responseCode = "404", description = "Delegacia não encontrada", content = @Content)
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<DelegaciaResponseDTO> buscarDelegaciaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(delegaciaService.getById(id));
    }

    @Operation(summary = "Cadastrar nova delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delegacia criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<DelegaciaResponseDTO> cadastrarDelegacia(
            @RequestPart("delegacia") String delegaciaJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            DelegaciaRequestDTO delegaciaDTO = objectMapper.readValue(delegaciaJson, DelegaciaRequestDTO.class);
            DelegaciaResponseDTO response = delegaciaService.createDelegacia(delegaciaDTO, imagem);
            return ResponseEntity
                    .created(URI.create("Imagens/Delegacias/" + response.getId()))
                    .body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Atualizar delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delegacia atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Delegacia não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados")
    })
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<DelegaciaResponseDTO> atualizarDelegacia(
            @PathVariable Long id,
            @RequestPart("delegacia") String delegaciaJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            DelegaciaRequestDTO delegaciaDTO = objectMapper.readValue(delegaciaJson, DelegaciaRequestDTO.class);
            DelegaciaResponseDTO response = delegaciaService.updateDelegacia(id, delegaciaDTO, imagem);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary = "Deletar uma delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delegacia deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Delegacia não encontrada", content = @Content)
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarDelegacia(@PathVariable Long id) {
        delegaciaService.deleteDelegacia(id);
        return ResponseEntity.noContent().build();
    }
}