package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvolvimentosInqueritoDTO {
    private List<PessoaEnvolvimentoDTO> pessoas;
    private List<BemEnvolvimentoDTO> bens;
}
