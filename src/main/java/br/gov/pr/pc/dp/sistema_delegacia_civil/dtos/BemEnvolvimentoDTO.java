package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BemEnvolvimentoDTO {
    private Long id;
    private Long bemId;
    private String tipoEnvolvimento;
    private String descricaoBem;
}