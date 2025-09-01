package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Veiculo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculo")
@Tag(name = "Veículos", description = "Operações relacionadas aos veículos cadastrados")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Operation(
            summary = "Lista todos os veículos",
            description = "Retorna uma lista com todos os veículos cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao buscar veículos")
            }
    )
    @GetMapping("/list")
    public ResponseEntity<List<Veiculo>> getAllVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoService.listarTodos();
            return ResponseEntity.ok(veiculos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Busca veículo por ID",
            description = "Retorna os dados de um veículo específico pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
            }
    )
    @GetMapping("/list/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(
            @Parameter(description = "ID do veículo") @PathVariable Long id) {
        try {
            Veiculo veiculo = veiculoService.buscarPorId(id);
            return ResponseEntity.ok(veiculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Cria um novo veículo",
            description = "Salva um novo veículo no sistema.",
            requestBody = @RequestBody(
                    description = "Objeto veículo a ser criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Veiculo.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao criar o veículo")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Veiculo> createVeiculo(
            @RequestPart("veiculo") Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = veiculoService.salvar(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Atualiza um veículo existente",
            description = "Atualiza os dados de um veículo pelo ID.",
            requestBody = @RequestBody(
                    description = "Objeto veículo com novos dados",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Veiculo.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao atualizar veículo")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(
            @Parameter(description = "ID do veículo") @PathVariable Long id,
            @RequestPart("veiculo") Veiculo veiculo) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Deleta um veículo",
            description = "Remove um veículo pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVeiculo(
            @Parameter(description = "ID do veículo") @PathVariable Long id) {
        try {
            veiculoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
