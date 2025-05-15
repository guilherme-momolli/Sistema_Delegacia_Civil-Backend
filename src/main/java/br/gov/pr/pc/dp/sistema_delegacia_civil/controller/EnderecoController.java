package br.gov.pr.pc.dp.sistema_delegacia_civil.controller;

import br.gov.pr.pc.dp.sistema_delegacia_civil.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Endereco;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/list")
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        try {
            List<Endereco> enderecos = enderecoService.listEndereco();
            return ResponseEntity.ok(enderecos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
        try {
            Endereco endereco = enderecoService.getById(id);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        try {
            Endereco novoEndereco = enderecoService.createEndereco(endereco);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.updateEndereco(id, endereco);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        try {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
