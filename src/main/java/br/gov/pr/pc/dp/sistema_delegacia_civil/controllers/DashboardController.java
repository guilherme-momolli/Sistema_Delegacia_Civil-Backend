package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.dashboard.DashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "Painel de indicadores e dados resumidos do sistema")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Obter dados gerais do dashboard")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados obtidos com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar os dados")
    })
    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard() {
        try {
            DashboardResponseDTO response = dashboardService.getDashboardResumo();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
//    @Operation(summary = "Obter resumo de inquéritos policiais", description = "Retorna estatísticas e informações resumidas sobre os inquéritos policiais.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Resumo de inquéritos retornado com sucesso"),
//            @ApiResponse(responseCode = "500", description = "Erro interno ao gerar os dados", content = @Content)
//    })
//    @GetMapping("/inqueritos")
//    public ResponseEntity<?> obterResumoInqueritos() {
//        return ResponseEntity.ok(dashboardService.getInqueritoResumo());
//    }

    // 🔹 Espaço reservado para os próximos endpoints (ex: boletins, apreensões, indicadores etc.)
    // @GetMapping("/boletins")
    // public ResponseEntity<?> obterResumoBoletins() {
    //     return ResponseEntity.ok(dashboardService.getBoletimResumo());
    // }
}