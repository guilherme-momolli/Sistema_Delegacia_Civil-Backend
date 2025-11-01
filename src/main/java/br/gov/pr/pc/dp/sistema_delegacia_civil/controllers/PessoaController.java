package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import java.util.Map;


import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Operações relacionadas às pessoas cadastradas")
public class PessoaController {

    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    @Operation(summary = "Listar todas as pessoas")
    @GetMapping("/list")
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listPessoa());
    }

    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarPessoaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.getById(id));
    }

    @Operation(summary = "Pesquisar pessoas com filtros e paginação")
    @GetMapping("/search")
    public ResponseEntity<Page<PessoaResponseDTO>> pesquisarPessoas(
            PessoaFiltroDTO filtro,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<String> camposValidos = List.of("nome", "cpf", "rg", "sexo", "situacaoPessoa");
        if (pageable.getSort().stream().anyMatch(order -> !camposValidos.contains(order.getProperty()))) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome").ascending());
        }
        return ResponseEntity.ok(pessoaService.buscarComFiltro(filtro, pageable));
    }

    @Operation(summary = "Cadastrar nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PostMapping(path = "/create",consumes = {"multipart/form-data"})
    public ResponseEntity<PessoaResponseDTO> cadastrarPessoa(
            @RequestPart("pessoa") String pessoaJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            PessoaRequestDTO pessoaDTO = objectMapper.readValue(pessoaJson, PessoaRequestDTO.class);
            PessoaResponseDTO response = pessoaService.createPessoa(pessoaDTO, imagem);
            return ResponseEntity
                    .created(URI.create("Imagens/Pessoas/" + response.getId()))
                    .body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary = "Atualizar pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou conflito de CPF")
    })
    @PutMapping(path = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<PessoaResponseDTO> atualizarPessoa(
            @PathVariable Long id,
            @RequestPart("pessoa") String pessoaJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        try {
            PessoaRequestDTO pessoaDTO = objectMapper.readValue(pessoaJson, PessoaRequestDTO.class);
            PessoaResponseDTO response = pessoaService.updatePessoa(id, pessoaDTO, imagem);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Deletar uma pessoa")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }
}