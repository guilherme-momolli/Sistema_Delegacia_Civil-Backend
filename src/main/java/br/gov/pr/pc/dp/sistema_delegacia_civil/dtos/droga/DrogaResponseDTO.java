package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.UnidadeMedida;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.TipoDroga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrogaResponseDTO {
    private Long id;
    private Long bemId;
    private TipoDroga tipoDroga;
    private String nomePopular;
    private BigDecimal quantidade;
    private String quantidadePorExtenso;
    private UnidadeMedida unidadeMedida;
}