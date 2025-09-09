package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.BoletimOcorrenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/boletim_ocorrencia")
@RequiredArgsConstructor
@Tag(name = "Boletins de Ocorrência", description = "Gerenciamento de boletins de ocorrência")
public class BoletimOcorrenciaController {

    private final BoletimOcorrenciaService boletimService;

    @Operation(summary = "Listar todos os boletins de ocorrência")
    @GetMapping("/list")
    public ResponseEntity<List<BoletimOcorrenciaResponseDTO>> getAll() {
        List<BoletimOcorrenciaResponseDTO> response = boletimService.findAll()
                .stream()
                .map(BoletimOcorrenciaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar boletim de ocorrência por ID")
    @GetMapping("/{id}")
    public ResponseEntity<BoletimOcorrenciaResponseDTO> getById(@PathVariable Long id) {
        var boletim = boletimService.findById(id);
        return ResponseEntity.ok(BoletimOcorrenciaResponseDTO.fromEntity(boletim));
    }

    @Operation(summary = "Buscar boletins por delegacia")
    @GetMapping("/delegacia/{delegaciaId}")
    public ResponseEntity<List<BoletimOcorrenciaResponseDTO>> getByDelegacia(@PathVariable Long delegaciaId) {
        List<BoletimOcorrenciaResponseDTO> response = boletimService.getBoletinsByDelegacia(delegaciaId)
                .stream()
                .map(BoletimOcorrenciaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Criar novo boletim de ocorrência")
    @PostMapping("/create")
    public ResponseEntity<BoletimOcorrenciaResponseDTO> create(
            @Valid @RequestBody BoletimOcorrenciaRequestDTO requestDTO) {

        var boletim = boletimService.createBoletimOcorrencia(requestDTO.toEntity(), requestDTO.getPessoasEnvolvidas());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BoletimOcorrenciaResponseDTO.fromEntity(boletim));
    }

    @Operation(summary = "Atualizar boletim de ocorrência")
    @PutMapping("/update/{id}")
    public ResponseEntity<BoletimOcorrenciaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody BoletimOcorrenciaRequestDTO requestDTO) {

        var boletim = boletimService.updateBoletim(id, requestDTO.toEntity(), requestDTO.getPessoasEnvolvidas());
        return ResponseEntity.ok(BoletimOcorrenciaResponseDTO.fromEntity(boletim));
    }

    @Operation(summary = "Excluir boletim de ocorrência")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boletimService.delete(id);
        return ResponseEntity.noContent().build();
    }
}