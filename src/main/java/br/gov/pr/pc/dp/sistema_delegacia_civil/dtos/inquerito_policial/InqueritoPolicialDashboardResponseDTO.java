package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InqueritoPolicialDashboardResponseDTO {
    private long totalInqueritosPoliciais;
    private Map<OrigemForcaPolicial, Long> totalPorOrigemForcaPolicial;
    private Map<SituacaoInquerito, Long>  totalPorSituacaoInquerito;
}