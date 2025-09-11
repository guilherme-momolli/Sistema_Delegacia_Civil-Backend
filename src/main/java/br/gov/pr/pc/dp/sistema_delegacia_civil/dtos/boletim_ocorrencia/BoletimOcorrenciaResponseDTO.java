package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletimOcorrenciaResponseDTO {

    private Long id;
    private OrigemForcaPolicial origemForcaPolicial;
    private LocalDateTime dataOcorrencia;
    private String boletim;
    private String natureza;
    private String representacao;
    private EnderecoResponseDTO endereco;
    private Long delegaciaId;
    private List<PessoaEnvolvimentoResponseDTO> pessoasEnvolvidas = new ArrayList<>();

}
