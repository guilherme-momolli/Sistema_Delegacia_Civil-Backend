package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.InqueritoPolicialMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.InqueritoPolicialService;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.InqueritoExcelExporter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/inquerito_policial")
@RequiredArgsConstructor
@Tag(name = "Inquérito Policial", description = "Gerenciamento de inquéritos policiais")
public class InqueritoPolicialController {

    private final InqueritoPolicialService inqueritoService;

    @Operation(summary = "Listar todos os inquéritos")
    @GetMapping("/list")
    public ResponseEntity<List<InqueritoPolicialResponseDTO>> getAll() {
        List<InqueritoPolicialResponseDTO> response = inqueritoService.findAll()
                .stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar inquérito por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InqueritoPolicialResponseDTO> getById(@PathVariable Long id) {
        var inquerito = inqueritoService.findById(id);
        return ResponseEntity.ok(InqueritoPolicialMapper.toResponseDTO(inquerito));
    }

    @Operation(summary = "Buscar inquéritos por delegacia")
    @GetMapping("/delegacia/{delegaciaId}")
    public ResponseEntity<List<InqueritoPolicialResponseDTO>> getByDelegacia(@PathVariable Long delegaciaId) {
        List<InqueritoPolicialResponseDTO> response = inqueritoService.getInqueritosByDelegacia(delegaciaId)
                .stream()
                .map(InqueritoPolicialMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Criar novo inquérito policial")
    @PostMapping("/create")
    public ResponseEntity<InqueritoPolicialResponseDTO> create(
            @Valid @RequestBody InqueritoPolicialRequestDTO requestDTO) {

        var inquerito = inqueritoService.createInqueritoPolicial(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InqueritoPolicialMapper.toResponseDTO(inquerito));
    }

    @Operation(summary = "Atualizar inquérito policial")
    @PutMapping("/update/{id}")
    public ResponseEntity<InqueritoPolicialResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InqueritoPolicialRequestDTO requestDTO) {

        var updated = inqueritoService.updateInqueritoPolicial(id, requestDTO);
        return ResponseEntity.ok(InqueritoPolicialMapper.toResponseDTO(updated));
    }

    @Operation(summary = "Excluir inquérito policial")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inqueritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

