package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InqueritoPolicialResponseDTO {
    private Long id;
    private String numeroJustica;
    private Long ordemIp;
    private LocalDate data;
    private Peca peca;
    private SituacaoInquerito situacaoInquerito;
    private OrigemForcaPolicial origemForcaPolicial;
    private String naturezaDoDelito;
    private String observacao;
    private Long delegaciaId;
    private List<PessoaEnvolvimentoResponseDTO> pessoasEnvolvidas = new ArrayList<>();
    private List<BemEnvolvimentoResponseDTO> bensEnvolvidos = new ArrayList<>();

}
