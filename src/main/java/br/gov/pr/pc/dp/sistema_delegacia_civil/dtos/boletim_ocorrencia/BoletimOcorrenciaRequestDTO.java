package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletimOcorrenciaRequestDTO {

    private OrigemForcaPolicial origemForcaPolicial;
    private LocalDateTime dataOcorrencia;
    private String boletim;
    private String natureza;
    private String representacao;
    private EnderecoRequestDTO endereco;
    private Long delegaciaId;
    private List<PessoaEnvolvimentoRequestDTO> pessoasEnvolvidas;

    public BoletimOcorrencia toEntity() {
        BoletimOcorrencia boletim = new BoletimOcorrencia();
        boletim.setOrigemForcaPolicial(this.origemForcaPolicial);
        boletim.setDataOcorrencia(this.dataOcorrencia);
        boletim.setBoletim(this.boletim);
        boletim.setNatureza(this.natureza);
        boletim.setRepresentacao(this.representacao);

        if (this.endereco != null) {
            boletim.setEndereco(this.endereco.toEntity());
        }

        if (this.delegaciaId != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(this.delegaciaId);
            boletim.setDelegacia(delegacia);
        }

        return boletim;
    }

}
