package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmaResponseDTO {
    private Long id;
    private BemResponseDTO bemId;
    private String tipoArmaFogo;
    private String especie;
    private String calibre;
    private String numeroPorte;
    private String numeroSerie;
    private String numeroRegistro;
    private String capacidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
