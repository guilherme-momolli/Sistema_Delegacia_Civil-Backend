package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.ViaCepResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.CepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
@RequiredArgsConstructor
@Tag(name = "CEP", description = "Consulta de endereço pelo CEP usando API ViaCEP")
public class CepController {

    private final CepService cepService;

    @Operation(summary = "Buscar endereço pelo CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponseDTO> buscarCep(@PathVariable String cep) {
        ViaCepResponseDTO endereco = cepService.consultarCep(cep);
        return ResponseEntity.ok(endereco);
    }
}
