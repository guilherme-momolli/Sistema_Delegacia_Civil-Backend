package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.TipoEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEnvolvimentoRequestDTO {

    private Long id;
    private Long pessoaId;
    private Long boletimId;
    private Long inqueritoId;
    private TipoEnvolvimento tipoEnvolvimento;
    private String observacao;

}
