package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.erro;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Resposta padrão de erro")
public class ErrorResponseDTO {

    @Schema(description = "Código do erro", example = "AUTH_001")
    private String code;

    @Schema(description = "Mensagem amigável", example = "Credenciais inválidas")
    private String message;

    @Schema(description = "Caminho da requisição", example = "/auth/login")
    private String path;

    @Schema(description = "Status HTTP", example = "401")
    private int status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data/hora do erro", example = "11/11/2025 15:14:22")
    private LocalDateTime timestamp;
}
