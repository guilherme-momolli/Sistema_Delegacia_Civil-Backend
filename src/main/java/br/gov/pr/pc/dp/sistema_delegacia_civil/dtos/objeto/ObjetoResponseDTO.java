package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjetoResponseDTO {
    private Long id;
    private Long bemId;
    private String tipoObjeto;
    private String numeroSerie;
    private String cor;
    private String material;
    private String dimensoes;
    private String estadoConservacao;
    private String situacaoObjeto;
}