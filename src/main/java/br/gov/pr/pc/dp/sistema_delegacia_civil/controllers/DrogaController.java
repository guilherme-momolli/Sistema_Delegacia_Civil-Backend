package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.DrogaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/droga")
@Tag(name = "Droga", description = "Endpoints para gerenciamento de drogas")
public class DrogaController {

    private final DrogaService drogaService;

    @Operation(summary = "Listar todas as drogas")
    @GetMapping("/list")
    public ResponseEntity<List<Droga>> getAllDrogas() {
        return ResponseEntity.ok(drogaService.listarTodas());
    }

    @Operation(summary = "Buscar droga por ID")
    @GetMapping("/list/{id}")
    public ResponseEntity<Droga> getDrogaById(@PathVariable Long id) {
        Droga droga = drogaService.buscarPorId(id);
        return droga != null ? ResponseEntity.ok(droga) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar nova droga")
    @PostMapping("/create")
    public ResponseEntity<Droga> salvarDroga(@RequestPart("droga") Droga droga) {
        Droga nova = drogaService.salvar(droga);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @Operation(summary = "Atualizar dados da droga")
    @PutMapping("/update/{id}")
    public ResponseEntity<Droga> atualizarDroga(@PathVariable Long id, @RequestPart("droga") Droga droga) {
        Droga atualizada = drogaService.atualizar(id, droga);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Excluir droga por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDroga(@PathVariable Long id) {
        drogaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
