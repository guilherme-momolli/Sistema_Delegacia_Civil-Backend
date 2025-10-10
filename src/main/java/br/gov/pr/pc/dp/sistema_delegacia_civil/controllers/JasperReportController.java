package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.BoletimOcorrenciaService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.JasperReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin
@AllArgsConstructor
public class JasperReportController {

    @Autowired
    private final JasperReportService jasperReportService;
    private final BoletimOcorrenciaService boletimOcorrenciaService;

    @GetMapping(
            value = "/boletim/{id}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> gerarBoletim(@PathVariable Long id) throws Exception {
        BoletimOcorrencia boletim = boletimOcorrenciaService.findById(id);

        Map<String, Object> params = new HashMap<>();
        params.put("delegaciaNome", boletim.getDelegacia().getNome());
        params.put("dataAtual", LocalDate.now().toString());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(boletim), false);

        byte[] pdf = jasperReportService.gerarRelatorio(params, dataSource, "boletim_ocorrencia");

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=boletim_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
