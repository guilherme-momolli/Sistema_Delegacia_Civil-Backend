package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDashboardResponseDTO {
    private long totalPessoas;
    private Map<Genero, Long> totalPorGenero;
    private Map<Etnia, Long> totalPorEtnia;
    private Map<EstadoCivil, Long> totalPorEstadoCivil;
    private Map<SituacaoPessoa, Long> totalPorSituacaoPessoa;
}
