package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.auth.AuthRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.auth.AuthResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Login de usuários e geração de token JWT")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Autenticar usuário", description = "Realiza login e retorna token JWT se credenciais estiverem corretas.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        try {
            AuthResponseDTO response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            log.warn("Falha na autenticação: Credenciais inválidas para o usuário {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            log.error("Erro ao processar login para o usuário {}: {}", request.getEmail(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
