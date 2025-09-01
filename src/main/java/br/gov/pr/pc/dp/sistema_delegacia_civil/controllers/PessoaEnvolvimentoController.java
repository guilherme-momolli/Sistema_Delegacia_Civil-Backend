package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.PessoaEnvolvimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa_envolvimento")
@Tag(name = "Pessoa Envolvida", description = "Gerenciamento das pessoas envolvidas em boletins e inquéritos")
public class PessoaEnvolvimentoController {

   private final PessoaEnvolvimentoService service;

    private final PessoaEnvolvimentoService pessoaEnvolvimentoService;

    @GetMapping("/inquerito/{inqueritoId}")
    public ResponseEntity<List<PessoaEnvolvimentoDTO>> listarPorInquerito(@PathVariable Long inqueritoId) {
        return ResponseEntity.ok(pessoaEnvolvimentoService.listarPorInquerito(inqueritoId));
    }

    @GetMapping("/boletim/{boletimId}")
    public ResponseEntity<List<PessoaEnvolvimentoDTO>> listarPorBoletim(@PathVariable Long boletimId) {
        return ResponseEntity.ok(pessoaEnvolvimentoService.listarPorBoletim(boletimId));
    }
}
