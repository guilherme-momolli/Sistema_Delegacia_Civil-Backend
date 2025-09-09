package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<PessoaEnvolvimentoResponseDTO> pessoasEnvolvidas;

    public static BoletimOcorrenciaResponseDTO fromEntity(BoletimOcorrencia entity) {
        if (entity == null) return null;

        BoletimOcorrenciaResponseDTO dto = new BoletimOcorrenciaResponseDTO();
        dto.setId(entity.getId());
        dto.setBoletim(entity.getBoletim());
        dto.setNatureza(entity.getNatureza());
        dto.setRepresentacao(entity.getRepresentacao());
        dto.setDataOcorrencia(entity.getDataOcorrencia());
        dto.setOrigemForcaPolicial(entity.getOrigemForcaPolicial());
        dto.setDelegaciaId(entity.getDelegacia() != null ? entity.getDelegacia().getId() : null);

        dto.setEndereco(entity.getEndereco() != null
                ? EnderecoResponseDTO.fromEntity(entity.getEndereco())
                : null);

        dto.setPessoasEnvolvidas(entity.getPessoasEnvolvidas() != null
                ? entity.getPessoasEnvolvidas().stream()
                .map(PessoaEnvolvimentoResponseDTO::fromEntity)
                .toList()
                : null);

        return dto;
    }
}
