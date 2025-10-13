package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<PessoaEnvolvimentoRequestDTO> pessoasEnvolvidas = new ArrayList<>();
    private List<BemEnvolvimentoRequestDTO> bensEnvolvidos = new ArrayList<>();
    
}
