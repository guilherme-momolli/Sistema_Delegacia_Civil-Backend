//package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers.auth;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.auth.AuthRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.AuthResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.services.auth.AuthService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth1")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequiredArgsConstructor
//@Tag(name = "Autenticação", description = "Login com JWT e gerenciamento de sessão")
//public class AuthController {
//
//    private final AuthService authService;
//
//    @PostMapping("/login")
//    @Operation(
//            summary = "Login do usuário",
//            description = "Autentica com email e senha. Retorna token JWT com expiração e tipo Bearer.",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Login bem-sucedido",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = AuthResponseDTO.class)
//                            )
//                    ),
//                    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados de entrada"),
//                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
//                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
//                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
//            }
//    )
//    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) {
//        AuthResponseDTO response = authService.login(request);
//        return ResponseEntity.ok(response);
//    }
//}
