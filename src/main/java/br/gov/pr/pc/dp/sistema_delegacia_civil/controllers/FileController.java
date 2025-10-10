package br.gov.pr.pc.dp.sistema_delegacia_civil.controllers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.services.FileStorageService;
import br.gov.pr.pc.dp.sistema_delegacia_civil.vos.UploadFileResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("file/v1")
@Tag(name = "Arquivos", description = "Operações de upload e download de arquivos")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    private static final Logger logger = Logger.getLogger(FileController.class.getName());

    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "application/pdf", "image/gif", "image/tiff", "image/bmp"
    );

    @Operation(
            summary = "Download de arquivo",
            description = "Faz o download de um arquivo com o nome especificado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Arquivo encontrado",
                            content = @Content(mediaType = "application/octet-stream")),
                    @ApiResponse(responseCode = "404", description = "Arquivo não encontrado")
            }
    )
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, String subFolder, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName, subFolder);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            logger.info("Não foi possível determinar o tipo do arquivo.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Operation(
            summary = "Upload de arquivo",
            description = "Faz o upload de um único arquivo.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso",
                            content = @Content(schema = @Schema(implementation = UploadFileResponseVO.class))),
                    @ApiResponse(responseCode = "400", description = "Tipo de arquivo inválido")
            }
    )
    @PostMapping(path = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "subFolder", required = false) String subFolder) {
        if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
            throw new RuntimeException("Tipo de arquivo inválido.");
        }
        String fileName = fileStorageService.storeFile(file, subFolder);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/v1/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @Operation(
            summary = "Upload de múltiplos arquivos",
            description = "Faz o upload de vários arquivos de uma vez.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Arquivos enviados com sucesso",
                            content = @Content(schema = @Schema(implementation = UploadFileResponseVO.class)))
            }
    )
    @PostMapping(path = "/uploadMultipleFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, String subFolder) {
        List<UploadFileResponseVO> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            responses.add(uploadFile(file, subFolder));
        }
        return responses;
    }

    @Operation(
            summary = "Exibe imagem ou arquivo direto no navegador",
            description = "Retorna os bytes de um arquivo armazenado, útil para exibir imagens diretamente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Arquivo retornado com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao carregar o arquivo")
            }
    )
    @GetMapping("/getFile/Imagens/**")
    public ResponseEntity<byte[]> getImagem(HttpServletRequest request) {
        try {

            String filePath = request.getRequestURI().split("/getFile/Imagens/")[1];

            String subFolder = filePath.substring(0, filePath.lastIndexOf('/'));
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

            byte[] imageBytes = fileStorageService.getImagem(fileName, subFolder);
            Resource resource = fileStorageService.loadFileAsResource(fileName, subFolder);

            String contentType = Files.probeContentType(resource.getFile().toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }       
}
