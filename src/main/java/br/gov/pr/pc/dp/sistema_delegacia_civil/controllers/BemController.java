package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/bens")
@Tag(name = "Bens", description = "Operações relacionadas aos bens cadastrados")
public class BemController {

    private final BemService bemService;
    private final ObjectMapper objectMapper;

    @Operation(summary = "Listar todos os bens cadastrados")
    @GetMapping("/list")
    public ResponseEntity<List<BemResponseDTO>> listarBens() {
        return ResponseEntity.ok(bemService.listBens());
    }

    @Operation(summary = "Buscar bem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bem encontrado"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado", content = @Content)
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<BemResponseDTO> buscarBemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bemService.getById(id));
    }

    @Operation(summary = "Pesquisar bens com filtros e paginação")
    @GetMapping("/search")
    public ResponseEntity<Page<BemResponseDTO>> pesquisarBens(
            BemFiltroDTO filtro,
            @PageableDefault(sort = "tipoBem", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<String> camposValidos = List.of("tipoBem", "descricao", "numeroSerie", "situacao", "dataCadastro");

        if (pageable.getSort().stream().anyMatch(order -> !camposValidos.contains(order.getProperty()))) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("tipoBem").ascending());
        }

        return ResponseEntity.ok(bemService.filtrarBens(filtro, pageable));
    }


    @Operation(summary = "Cadastrar novo bem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bem criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<BemResponseDTO> cadastrarBem(
            @RequestPart("bem") String bemJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            BemRequestDTO bemDTO = objectMapper.readValue(bemJson, BemRequestDTO.class);
            BemResponseDTO response = bemService.createBem(bemDTO, imagem);
            return ResponseEntity
                    .created(URI.create("Imagens/Bens/" + response.getId()))
                    .body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Atualizar bem existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bem atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<BemResponseDTO> atualizarBem(
            @PathVariable Long id,
            @RequestPart("bem") String bemJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            BemRequestDTO bemDTO = objectMapper.readValue(bemJson, BemRequestDTO.class);
            BemResponseDTO response = bemService.updateBem(id, bemDTO, imagem);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Deletar um bem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bem deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarBem(@PathVariable Long id) {
        bemService.deleteBem(id);
        return ResponseEntity.noContent().build();
    }
}