package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.BoletimOcorrenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/boletim_ocorrencia")
@Tag(name = "Boletins de Ocorrência", description = "Gerenciamento de boletins de ocorrência")
public class BoletimOcorrenciaController {

    @Autowired
    private BoletimOcorrenciaService service;

    @Operation(summary = "Listar todos os boletins de ocorrência")
    @GetMapping("/list")
    public ResponseEntity<List<BoletimOcorrencia>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Buscar boletim por ID")
    @ApiResponse(responseCode = "200", description = "Boletim encontrado")
    @ApiResponse(responseCode = "404", description = "Boletim não encontrado")
    @GetMapping("/getById/{id}")
    public ResponseEntity<BoletimOcorrencia> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar boletins por delegacia")
    @ApiResponse(responseCode = "200", description = "Boletins encontrados")
    @ApiResponse(responseCode = "404", description = "Delegacia não encontrada")
    @GetMapping("/delegacia/{delegaciaId}")
    public List<BoletimOcorrencia> getByDelegacia(@PathVariable Long delegaciaId) {
        return service.getByDelegacia(delegaciaId);
    }

    @Operation(summary = "Criar um novo boletim de ocorrência")
    @PostMapping("/create")
    public ResponseEntity<BoletimOcorrencia> create(@RequestBody BoletimOcorrencia boletim) {
        BoletimOcorrencia saved = service.save(boletim);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Atualizar um boletim de ocorrência existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<BoletimOcorrencia> update(@PathVariable Long id, @RequestBody BoletimOcorrencia boletim) {
        try {
            BoletimOcorrencia updated = service.updateBoletimOcorrencia(id, boletim);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um boletim de ocorrência")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBoletimOcorrencia(id);
        return ResponseEntity.noContent().build();
    }
}