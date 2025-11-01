package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.veiculo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDashboardResponseDTO {
    private Map<Cambio, Long> totalPorCambio;
    private Map<Carroceria, Long> totalPorCarroceria;
    private Map<CategoriaVeiculo, Long> totalPorCategoriaVeiculo;
    private Map<Combustivel, Long> totalPorCombustivel;
    private Map<EspecieVeiculo, Long> totalPorEspecieVeiculo;
    private Map<SituacaoLicenciamento, Long> totalPorSituacaoLicenciamento;
    private Map<SituacaoVeiculo, Long> totalPorSituacaoVeiculo;
    private Map<TipoTracao, Long> totalPorTipoTracao;
    private Map<TipoVeiculo, Long> totalPorTipoVeiculo;
}
