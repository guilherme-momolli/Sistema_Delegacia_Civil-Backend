package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmaResponseDTO {
    private Long id;
    private Long bemId;
    private TipoArmaFogo tipoArmaFogo;
    private EspecieArma especieArma;
    private Calibre calibre;
    private String numeroPorte;
    private String numeroSerie;
    private String numeroRegistro;
    private String capacidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
