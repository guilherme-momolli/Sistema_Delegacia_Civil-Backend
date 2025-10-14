package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.InqueritoPolicialService;
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
@RequestMapping("/inquerito_policial")
@RequiredArgsConstructor
@Tag(name = "Inquéritos Policiais", description = "Gerenciamento dos inquéritos policiais")
public class InqueritoPolicialController {

    private final InqueritoPolicialService inqueritoService;

    @Operation(summary = "Listar todos os inquéritos policiais")
    @GetMapping("/list")
    public ResponseEntity<List<InqueritoPolicialResponseDTO>> getAll() {
        List<InqueritoPolicialResponseDTO> response = inqueritoService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar inquérito policial por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InqueritoPolicialResponseDTO> getById(@PathVariable Long id) {
        InqueritoPolicialResponseDTO response = inqueritoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar inquéritos policiais de uma delegacia específica")
    @GetMapping("/delegacia/{delegaciaId}")
    public ResponseEntity<List<InqueritoPolicialResponseDTO>> getByDelegacia(@PathVariable Long delegaciaId) {
        List<InqueritoPolicialResponseDTO> response = inqueritoService.getInqueritosByDelegacia(delegaciaId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Criar um novo inquérito policial")
    @PostMapping("/create")
    public ResponseEntity<InqueritoPolicialResponseDTO> create(
            @Valid @RequestBody InqueritoPolicialRequestDTO requestDTO) {
        try{
            InqueritoPolicialResponseDTO response = inqueritoService.createInqueritoPolicial(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Atualizar um inquérito policial existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<InqueritoPolicialResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InqueritoPolicialRequestDTO requestDTO) {
        InqueritoPolicialResponseDTO response = inqueritoService.updateInqueritoPolicial(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir um inquérito policial")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inqueritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}