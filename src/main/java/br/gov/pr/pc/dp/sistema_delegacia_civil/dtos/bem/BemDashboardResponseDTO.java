package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo.VeiculoDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.SituacaoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BemDashboardResponseDTO {

    private long totalBens;
    private Map<TipoBem, Long> totalPorTipoBem;
    private Map<SituacaoBem, Long> totalPorSituacaoBem;
    private ArmaDashboardResponseDTO armaDashboardResponse;
    private DrogaDashboardResponseDTO drogaDashboardResponse;
//    private ObjetoDashboardResponseDTO ObjetoDashboardResponseDTO;
    private VeiculoDashboardResponseDTO veiculoDashboardResponse;
}
