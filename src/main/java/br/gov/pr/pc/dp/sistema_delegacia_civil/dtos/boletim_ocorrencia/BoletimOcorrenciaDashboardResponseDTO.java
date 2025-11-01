package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoletimOcorrenciaDashboardResponseDTO {

    private long totalBoletinsOcorrencia;
    private Map<OrigemForcaPolicial, Long> totalPorOrigemForcaPolicial;
}
