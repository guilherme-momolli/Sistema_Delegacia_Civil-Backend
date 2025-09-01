package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InqueritoPolicialDTO {
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
}
