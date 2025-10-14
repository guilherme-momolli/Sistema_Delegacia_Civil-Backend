package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InqueritoPolicialRequestDTO {

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
    private List<PessoaEnvolvimentoRequestDTO> pessoasEnvolvidas = new ArrayList<>();
    private List<BemEnvolvimentoRequestDTO> bensEnvolvidos = new ArrayList<>();

}
