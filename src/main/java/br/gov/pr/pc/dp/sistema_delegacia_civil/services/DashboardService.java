package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.dashboard.DashboardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final InqueritoPolicialService inqueritoPolicialService;
    private final BoletimOcorrenciaService boletimOcorrenciaService;
    private final PessoaService pessoaService;
    private final BemService bemService;

    public DashboardResponseDTO getDashboardResumo() {
        DashboardResponseDTO dashboard = new DashboardResponseDTO();
        dashboard.setBoletimOcorrenciaDashboardResponse(boletimOcorrenciaService.getBoletimOcorrenciaResumo());
        dashboard.setInqueritoPolicialDashboardResponse(inqueritoPolicialService.getInqueritoResumo());
        dashboard.setPessoaDashboardResponse(pessoaService.getPessoaResumo());
        dashboard.setBemDashboardResponse(bemService.getBemResumo());
        return dashboard;
    }

}
