package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.InqueritoPolicialService;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.InqueritoExcelExporter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Inquerito Policial", description = "Operações relacionadas aos inqueritos policiais")
public class InqueritoPolicialController {

    @Autowired
    private InqueritoPolicialService inqueritoPolicialService;

    @Operation(summary = "Listar todos os inquéritos")
    @GetMapping("/list")
    public ResponseEntity<List<InqueritoPolicial>> getAll() {
        return ResponseEntity.ok(inqueritoPolicialService.findAll());
    }

//    @Operation(summary = "Listar envolvimentos em inquérito policial", description = "Retorna pessoas e bens relacionados ao inquérito.")
//    @ApiResponse(responseCode = "200", description = "Lista de envolvimentos retornada com sucesso")
//    @GetMapping("/{id}/envolvimentos")
//    public ResponseEntity<EnvolvimentosInqueritoDTO> listarEnvolvimentos(@PathVariable Long id) {
//        EnvolvimentosInqueritoDTO resposta = service.listarEnvolvimentos(id);
//        return ResponseEntity.ok(resposta);
//    }


    @Operation(summary = "Buscar inquérito por ID")
    @ApiResponse(responseCode = "200", description = "Inquérito encontrado")
    @ApiResponse(responseCode = "404", description = "Inquérito não encontrado")
    @GetMapping("/getById/{id}")
    public ResponseEntity<InqueritoPolicial> getById(@PathVariable Long id) {
        return inqueritoPolicialService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca todos inqueritos cadastrados no ID da Delegacia logada")
    @ApiResponse(responseCode = "200", description = "Delegacia encontrado")
    @ApiResponse(responseCode = "404", description = "Delegacia não encontrado")
    @GetMapping("/delegacia/{delegaciaId}")
    public List<InqueritoPolicial> getByDelegacia(@PathVariable Long delegaciaId) {
        return inqueritoPolicialService.getInqueritosByDelegacia(delegaciaId);
    }

//    @GetMapping("/export/excel")
//    public ResponseEntity<InputStreamResource> exportAllToExcel() throws IOException {
//        List<InqueritoPolicial> inqueritos = service.findAll();
//        ByteArrayInputStream in = InqueritoExcelExporter.exportToExcel(inqueritos);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=inqueritos.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .body(new InputStreamResource(in));
//    }
//
//    @GetMapping("/export/excel/instituicao/{instituicaoId}")
//    public ResponseEntity<InputStreamResource> exportByInstituicaoToExcel(@PathVariable Long instituicaoId) throws IOException {
//        List<InqueritoPolicial> inqueritos = service.getInqueritosByInstituicao(instituicaoId);
//        ByteArrayInputStream in = InqueritoExcelExporter.exportToExcel(inqueritos);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=inqueritos_instituicao_" + instituicaoId + ".xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .body(new InputStreamResource(in));
//    }


    @PostMapping("/create")
    public ResponseEntity<InqueritoPolicial> createInqueritoPolicial(
            @RequestBody InqueritoPolicialRequestDTO requestDTO) {

        InqueritoPolicial inquerito = requestDTO.toEntity();
        List<PessoaEnvolvimentoDTO> pessoas = requestDTO.getPessoasEnvolvidas();

        InqueritoPolicial saved = inqueritoPolicialService.createInqueritoPolicial(inquerito, pessoas);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<InqueritoPolicial> updateInqueritoPolicial(
            @PathVariable Long id,
            @RequestBody InqueritoPolicialRequestDTO requestDTO) {

        InqueritoPolicial inquerito = requestDTO.toEntity();
        List<PessoaEnvolvimentoDTO> pessoas = requestDTO.getPessoasEnvolvidas();

        InqueritoPolicial updated = inqueritoPolicialService.updateInqueritoPolicial(id, inquerito, pessoas);

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inqueritoPolicialService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
