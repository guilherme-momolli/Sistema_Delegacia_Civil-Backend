package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.SituacaoDroga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.TipoDroga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrogaDashboardResponseDTO {

    private Map<TipoDroga, Long> totalPorTipoDroga;
}
