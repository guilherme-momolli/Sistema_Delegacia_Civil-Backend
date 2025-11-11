package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.BemTipoEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BemEnvolvimentoResponseDTO {
    private Long id;
    private Long bemId;
    private Long boletimOcorrenciaId;
    private Long inqueritoPolicialId;
    private BemTipoEnvolvimento tipoEnvolvimento;
    private String observacao;
    private LocalDate dataEnvolvimento;
}
