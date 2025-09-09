package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public InqueritoPolicial toEntity() {
        InqueritoPolicial inquerito = new InqueritoPolicial();
        inquerito.setId(this.id);
        inquerito.setNumeroJustica(this.numeroJustica);
        inquerito.setOrdemIp(this.ordemIp);
        inquerito.setData(this.data);
        inquerito.setPeca(this.peca);
        inquerito.setSituacaoInquerito(this.situacaoInquerito);
        inquerito.setOrigemForcaPolicial(this.origemForcaPolicial);
        inquerito.setNaturezaDoDelito(this.naturezaDoDelito);
        inquerito.setObservacao(this.observacao);

        if (this.delegaciaId != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(this.delegaciaId);
            inquerito.setDelegacia(delegacia);
        }

        if (this.pessoasEnvolvidas != null) {
            List<PessoaEnvolvimento> pessoas = this.pessoasEnvolvidas.stream()
                    .map(dto -> {
                        PessoaEnvolvimento pessoa = dto.toEntity();
                        pessoa.setInqueritoPolicial(inquerito); // vincula a pessoa ao inquérito
                        return pessoa;
                    })
                    .collect(Collectors.toList());
            inquerito.setPessoasEnvolvidas(pessoas);
        }

        return inquerito;
    }
}
