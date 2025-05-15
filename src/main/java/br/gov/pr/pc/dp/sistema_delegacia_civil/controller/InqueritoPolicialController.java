package br.gov.pr.pc.dp.sistema_delegacia_civil.controller;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.service.InqueritoPolicialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/inquerito_policial")
public class InqueritoPolicialController {

    @Autowired
    private InqueritoPolicialService service;

    @GetMapping("/list")
    public ResponseEntity<List<InqueritoPolicial>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<InqueritoPolicial> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instituicao/{instituicaoId}")
    public List<InqueritoPolicial> getByInstituicao(@PathVariable Long instituicaoId) {
        return service.getInqueritosByInstituicao(instituicaoId);
    }


    @PostMapping("/create")
    public ResponseEntity<InqueritoPolicial> create(@RequestBody InqueritoPolicial inquerito) {
        InqueritoPolicial saved = service.save(inquerito);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<InqueritoPolicial> update(@PathVariable Long id, @RequestBody InqueritoPolicial inquerito) {
        try {
            InqueritoPolicial updated = service.update(id, inquerito);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
