package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mapper.PessoaMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Operações relacionadas aos pessoas cadastrados")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;


    @Operation(summary = "Listar todas as pessoas")
    @GetMapping("/list")
    public ResponseEntity<List<Pessoa>> listarPessoas() {

        return ResponseEntity.ok(pessoaService.listPessoa());
    }

    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    })

    @GetMapping("/getById/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.getById(id));
    }

    @Operation(summary = "Pesquisar pessoas com filtros e paginação")
    @GetMapping("/search")
    public ResponseEntity<Page<PessoaResponseDTO>> pesquisarPessoas(
            PessoaFiltroDTO filtro,
            Pageable pageable
    ) {
        Page<Pessoa> pessoas = pessoaService.buscarComFiltro(filtro, pageable);
        Page<PessoaResponseDTO> resultados = pessoas.map(pessoaMapper::toResponseDTO);
        return ResponseEntity.ok(resultados);
    }


    @Operation(summary = "Cadastrar nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados", content = @Content)
    })
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Pessoa> cadastrarPessoa(
            @RequestPart("pessoa") @Valid Pessoa pessoa,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        Pessoa pessoaCriada = pessoaService.createPessoa(pessoa, imagem);
        return ResponseEntity.created(URI.create("/pessoa/" + pessoaCriada.getId())).body(pessoaCriada);
    }

    @Operation(summary = "Atualizar pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou conflito de CPF")
    })
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Pessoa> atualizarPessoa(
            @PathVariable Long id,
            @RequestPart("pessoa") @Valid Pessoa pessoa,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        Pessoa pessoaAtualizada = pessoaService.updatePessoa(id, pessoa, imagem);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @Operation(summary = "Deletar uma pessoa")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }
}
