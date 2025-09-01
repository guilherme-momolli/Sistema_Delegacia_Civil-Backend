package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class EstatisticasDashboardDTO {
    private Map<String, Long> boletinsPorTipo;
    private Map<String, Long> inqueritosPorStatus;
    private Map<String, Long> apreensoesPorTipo;
    private Map<String, Long> boletinsPorMes;
}
