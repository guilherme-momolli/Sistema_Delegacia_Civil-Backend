package br.gov.pr.pc.dp.sistema_delegacia_civil.controller;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.service.InqueritoPolicialService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.InqueritoExcelExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<InqueritoPolicial> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instituicao/{instituicaoId}")
    public List<InqueritoPolicial> getByInstituicao(@PathVariable Long instituicaoId) {
        return service.getInqueritosByInstituicao(instituicaoId);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportAllToExcel() throws IOException {
        List<InqueritoPolicial> inqueritos = service.findAll();
        ByteArrayInputStream in = InqueritoExcelExporter.exportToExcel(inqueritos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=inqueritos.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/export/excel/instituicao/{instituicaoId}")
    public ResponseEntity<InputStreamResource> exportByInstituicaoToExcel(@PathVariable Long instituicaoId) throws IOException {
        List<InqueritoPolicial> inqueritos = service.getInqueritosByInstituicao(instituicaoId);
        ByteArrayInputStream in = InqueritoExcelExporter.exportToExcel(inqueritos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=inqueritos_instituicao_" + instituicaoId + ".xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
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
