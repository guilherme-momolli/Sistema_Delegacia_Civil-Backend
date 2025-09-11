package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {

    private final br.gov.pr.pc.dp.sistema_delegacia_civil.services.DashboardService dashboardService;

    @GetMapping("/totais")
    public ResponseEntity<Map<String, Object>> getTotais() {
        return ResponseEntity.ok(dashboardService.getTotais());
    }

    @GetMapping("/bos-por-mes")
    public ResponseEntity<List<Map<String, Object>>> getBosPorMes() {
        return ResponseEntity.ok(dashboardService.getBosPorMes());
    }

    @GetMapping("/inqueritos-por-delegacia")
    public ResponseEntity<List<Map<String, Object>>> getInqueritosPorDelegacia() {
        return ResponseEntity.ok(dashboardService.getInqueritosPorDelegacia());
    }

    @GetMapping("/bens-por-tipo")
    public ResponseEntity<List<Map<String, Object>>> getBensPorTipo() {
        return ResponseEntity.ok(dashboardService.getBensPorTipo());
    }
}
