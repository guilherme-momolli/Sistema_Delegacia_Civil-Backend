package br.gov.pr.pc.dp.sistema_delegacia_civil.controller;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dto.CadastroRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try{
            usuarioService.listUsuarios();
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        try{
            usuarioService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/instituicao/{instituicaoId}")
    public List<Usuario> getUsuariosByInstituicaoId(@PathVariable Long instituicaoId) {
        return usuarioService.getUsuariosByInstituicaoId(instituicaoId);
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(
            @RequestBody CadastroRequestDTO cadastroRequestDTO,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Usuario usuario = usuarioService.createUsuario(cadastroRequestDTO, token);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Usuario atualizado = usuarioService.updateUsuario(id, usuario, token);
            return ResponseEntity.status(HttpStatus.OK).body(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            usuarioService.deleteUsuario(id, token);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
