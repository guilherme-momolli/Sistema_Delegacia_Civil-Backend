package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.dashboard;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaDashboardResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {

    private InqueritoPolicialDashboardResponseDTO inqueritoPolicialDashboardResponse;
    private BoletimOcorrenciaDashboardResponseDTO boletimOcorrenciaDashboardResponse;
    private PessoaDashboardResponseDTO pessoaDashboardResponse;
    private BemDashboardResponseDTO bemDashboardResponse;
}
