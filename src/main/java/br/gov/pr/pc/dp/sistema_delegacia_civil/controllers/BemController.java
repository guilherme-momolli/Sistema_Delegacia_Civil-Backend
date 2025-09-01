package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.TipoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.BemService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bem")
@RequiredArgsConstructor
@Tag(name = "Bem", description = "Operações relacionadas aos bens (arma, veículo, objeto, droga)")
public class BemController {

    private final BemService bemService;
    private final FileStorageService fileStorageService;

    @Operation(summary = "Listar todos os bens")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/list")
    public ResponseEntity<List<Bem>> listarTodos() {
        return ResponseEntity.ok(bemService.listarBens());
    }

    @Operation(summary = "Buscar bem por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bem encontrado"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Bem>> buscarPorId(
            @Parameter(description = "ID do bem") @PathVariable Long id) {
        return ResponseEntity.ok(bemService.buscarPorId(id));
    }

    @Operation(summary = "Criar novo bem")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bem criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Bem> criarBem(@Valid @RequestPart("bem") Bem bem,
                                        @RequestPart("detalhesBem") String detalhesBemJson,
                                        @RequestPart(value = "imagem", required = false) MultipartFile imagem) throws JsonProcessingException {
        Object detalhesBem = mapearDetalhesBem(bem.getTipoBem(), detalhesBemJson);
        Bem bemCriado = bemService.salvarBemComDetalhes(bem, detalhesBem, imagem);
        return ResponseEntity.created(URI.create("/bem/" + bemCriado.getId())).body(bemCriado);
    }


    @Operation(summary = "Atualizar um bem existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bem atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Bem> atualizarBem(
            @Parameter(description = "ID do bem") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do bem") @Valid @RequestPart("bem") Bem bem,
            @Parameter(description = "Nova imagem (opcional)")
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        bem.setId(id);
        return ResponseEntity.ok(bemService.atualizar(bem, imagem));
    }

    @Operation(summary = "Deletar um bem")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bem deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarBem(
            @Parameter(description = "ID do bem") @PathVariable Long id) {
        bemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private Object mapearDetalhesBem(TipoBem tipoBem, String detalhesBemJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return switch (tipoBem) {
            case ARMA -> objectMapper.readValue(detalhesBemJson, Arma.class);
            case DROGA -> objectMapper.readValue(detalhesBemJson, Droga.class);
            case OBJETO -> objectMapper.readValue(detalhesBemJson, Objeto.class);
            case VEICULO -> objectMapper.readValue(detalhesBemJson, Veiculo.class);
            default -> throw new IllegalArgumentException("Tipo de bem desconhecido: " + tipoBem);
        };
    }

}
