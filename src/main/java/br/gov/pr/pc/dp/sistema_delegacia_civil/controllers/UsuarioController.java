package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.CadastroRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas aos usuarios cadastrados")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<Usuario> getUsuarioById(
            @Parameter(description = "ID do usuário") @PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Listar usuários por ID da delegacia")
    @GetMapping("/delegacia/{delegaciaId}")
    public List<Usuario> getUsuariosByDelegaciaId(
            @Parameter(description = "ID da delegacia") @PathVariable Long delegaciaId) {
        return usuarioService.getUsuariosByDelegaciaId(delegaciaId);
    }

    @Operation(summary = "Criar um novo usuário vinculado à delegacia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado ou token inválido")
    })
    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(
            @RequestBody CadastroRequestDTO cadastroRequestDTO){
        try {
            Usuario usuario = usuarioService.createUsuario(cadastroRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Atualizar dados de um usuário existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @Parameter(description = "ID do usuário") @PathVariable Long id,
            @RequestBody Usuario usuario) {
        try {
            Usuario atualizado = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Excluir usuário por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @Parameter(description = "ID do usuário") @PathVariable Long id){
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
