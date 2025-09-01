package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Objeto;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.ObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/objeto")
@Tag(name = "Objeto", description = "API para gerenciar objetos apreendidos")
public class ObjetoController {

    private final ObjetoService objetoService;

    @Operation(summary = "Listar todos os objetos")
    @GetMapping("/list")
    public ResponseEntity<List<Objeto>> getAllObjetos() {
        try {
            List<Objeto> objetos = objetoService.listarTodos();
            return ResponseEntity.ok(objetos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar objeto por ID")
    @GetMapping("/list/{id}")
    public ResponseEntity<Objeto> getObjetoById(
            @Parameter(description = "ID do objeto") @PathVariable Long id) {
        try {
            Objeto objeto = objetoService.buscarPorId(id);
            return ResponseEntity.ok(objeto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Criar novo objeto")
    @PostMapping("/create")
    public ResponseEntity<Objeto> salvarObjeto(
            @Parameter(description = "Objeto a ser criado") @RequestPart("objeto") Objeto objeto) {
        try {
            Objeto novoObjeto = objetoService.salvar(objeto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoObjeto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Atualizar objeto existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<Objeto> atualizarObjeto(
            @Parameter(description = "ID do objeto") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do objeto") @RequestPart("objeto") Objeto objeto) {
        try {
            Objeto objetoAtualizado = objetoService.atualizar(id, objeto);
            return ResponseEntity.ok(objetoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Excluir objeto por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDroga(
            @Parameter(description = "ID do objeto") @PathVariable Long id) {
        try {
            objetoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
