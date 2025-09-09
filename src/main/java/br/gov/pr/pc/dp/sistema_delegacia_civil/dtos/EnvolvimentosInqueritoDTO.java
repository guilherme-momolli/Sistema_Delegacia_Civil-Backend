package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvolvimentosInqueritoDTO {
    private List<PessoaEnvolvimentoRequestDTO> pessoas;
    private List<BemEnvolvimentoDTO> bens;
}
