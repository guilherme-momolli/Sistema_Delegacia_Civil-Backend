package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.dashboard.DashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaDashboardResponseDTO;

public class DashboardMapper {

    public static DashboardResponseDTO toDashboard(
            BoletimOcorrenciaDashboardResponseDTO boletimResumo,
            InqueritoPolicialDashboardResponseDTO inqueritoResumo,
            PessoaDashboardResponseDTO pessoaResumo,
            BemDashboardResponseDTO bemResumo
    ) {
        DashboardResponseDTO dashboard = new DashboardResponseDTO();
        dashboard.setBoletimOcorrenciaDashboardResponse(boletimResumo);
        dashboard.setInqueritoPolicialDashboardResponse(inqueritoResumo);
        dashboard.setPessoaDashboardResponse(pessoaResumo);
        dashboard.setBemDashboardResponse(bemResumo);
        return dashboard;
    }
}

