package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.SituacaoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmaDashboardResponseDTO {

    private Map<Calibre, Long> totalPorCalibre;
    private Map<EspecieArma, Long> totalPorEspecieArma;
    private Map<SituacaoArmaFogo, Long> totalPorSituacaoArmaFogo;
    private Map<TipoArmaFogo, Long> totalPorTipoArmaFogo;
}
