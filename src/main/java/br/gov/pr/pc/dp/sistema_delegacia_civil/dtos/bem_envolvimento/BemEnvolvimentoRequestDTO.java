package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.BemTipoEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BemEnvolvimentoRequestDTO {

    private Long id;
    private Long bemId;
    private Long boletimOcorrenciaId;
    private Long inqueritoPolicialId;
    private BemTipoEnvolvimento tipoEnvolvimento;
    private String observacao;
    private LocalDate dataEnvolvimento;
}
