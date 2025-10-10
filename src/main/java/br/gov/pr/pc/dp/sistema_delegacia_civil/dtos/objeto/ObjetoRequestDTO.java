package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.objeto;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetoRequestDTO {
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